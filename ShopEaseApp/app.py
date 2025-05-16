from flask import Flask, render_template, request, redirect, url_for, session, jsonify
from flask import flash
from werkzeug.security import check_password_hash, generate_password_hash

app = Flask(__name__)
app.secret_key = 'shopease-secret-key'

# Demo data
users = {
    'testuser': generate_password_hash('testpass')
}
products = [
    {'id': 1, 'name': 'Laptop', 'price': 700},
    {'id': 2, 'name': 'Smartphone', 'price': 300},
    {'id': 3, 'name': 'Headphones', 'price': 50}
]
orders = []

@app.route('/')
def home():
    if 'username' in session:
        return redirect(url_for('shop'))
    return render_template('login.html')

@app.route('/login', methods=['POST'])
def login():
    username = request.form['username']
    password = request.form['password']
    if username in users and check_password_hash(users[username], password):
        session['username'] = username
        session['cart'] = []
        return redirect(url_for('shop'))
    flash('Invalid credentials')
    return redirect(url_for('home'))

@app.route('/logout')
def logout():
    session.clear()
    return redirect(url_for('home'))

@app.route('/shop')
def shop():
    if 'username' not in session:
        return redirect(url_for('home'))
    return render_template('shop.html', products=products, cart=session.get('cart', []))

@app.route('/add_to_cart/<int:product_id>')
def add_to_cart(product_id):
    if 'username' not in session:
        return redirect(url_for('home'))
    cart = session.get('cart', [])
    for p in products:
        if p['id'] == product_id:
            cart.append(p)
            break
    session['cart'] = cart
    return redirect(url_for('shop'))

@app.route('/place_order')
def place_order():
    if 'username' not in session:
        return redirect(url_for('home'))
    cart = session.get('cart', [])
    if cart:
        orders.append({'username': session['username'], 'items': cart.copy()})
        session['cart'] = []
        flash('Order placed!')
    return redirect(url_for('shop'))

@app.route('/order_history')
def order_history():
    if 'username' not in session:
        return redirect(url_for('home'))
    user_orders = [o for o in orders if o['username'] == session['username']]
    return render_template('order_history.html', orders=user_orders)

# API endpoints
@app.route('/api/products')
def api_products():
    return jsonify(products)

@app.route('/api/orders', methods=['GET'])
def api_orders():
    username = request.args.get('username')
    user_orders = [o for o in orders if o['username'] == username]
    return jsonify(user_orders)

if __name__ == '__main__':
    app.run(debug=True)
