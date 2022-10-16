import * as React from 'react';
import { AppContext } from '../components/AppContext';
import { IItem, IItemInCart, ICart, API_ROOT } from '../App';
import { Link } from 'react-router-dom';
import CartItem from '../components/CartItem';

function Checkout() {

    const {cart, setCart} = React.useContext(AppContext);

    // In case any vals are used when the cart is still loading
    let subCost = 0; let totalCost = 0; let cartLength = 0;
    if (cart != null) {
        subCost = cart.subCost;
        totalCost = cart.totalCost;
        cartLength = cart.length;
    }

    const renderCartItems = () => {
        if (cart.length == 0) {
            return(<div className='text-sm py-4 mx-4 border-b'>
            There are no items in your cart. Go add some!
            </div>);
        }
        const itemsInCart: IItemInCart[] = cart.itemsInCart;
        const itemsList: React.ReactElement[] = [];
        itemsInCart.forEach((item: IItemInCart, index: number) => {
            itemsList.push(<CartItem itemInCart={item} key={index}/>);
        });
        return(<>{itemsList}</>);
    }

    const placeOrder = () => {
        const cartRequestUrl = API_ROOT + "/cart";
        console.log("Requesting new cart...");
        fetch(cartRequestUrl, { method: 'POST' })
        .then((response) => {
            response.json()
            .then((parsedJson) => {
                setCart(parsedJson);
                console.log("New cart received.");
            })
            .catch(() => {
                console.log("The cart was was either not created or received.");
            });
        });
    }

    return(<>
        <div className='flex w-screen h-screen justify-center flex-wrap bg-gray-100'>
            <div className='w-5/6 lg:w-2/3 xl:w-1/2 min-w-[580px]'>
                <div className='flex w-full mt-10 mb-14 justify-center text-4xl font-sans font-semibold'>
                    Review Cart and Checkout
                </div>
                <div className='w-full'>
                    <div className='w-[62.5%] float-left bg-white rounded shadow'>
                        <div className='text-2xl font-medium text-blue-900 mx-4 pt-2 pb-3 mb-2 border-b'>
                            Shopping Cart
                        </div>
                        {renderCartItems()}
                        <div className='w-full'>
                            <div className='float-left my-4 ml-4'>
                                <Link to='/'>
                                    <button className='transition duration-300 ease-in-out bg-blue-200 hover:bg-blue-300
                                    rounded-md px-2 py-1 shadow'>
                                        <span className='font-semibold text-blue-900'>Back to Shopping</span>
                                    </button>
                                </Link>
                            </div>
                            <div className='float-right mt-5 mr-4'>
                                {`Subtotal (${cartLength} Items):`}<span className='ml-1 font-bold'>${subCost.toFixed(2)}</span>
                            </div>
                        </div>
                    </div>
                    <div className='w-[32.5%] float-right bg-white rounded shadow'>
                        <div className='text-xl font-medium text-blue-900 mx-4 pt-2 pb-2 mb-2 border-b'>
                            Order Summary
                        </div>
                        <div className='mx-4 grid grid-cols-2 gap-1 pb-2 mb-2 border-b text-sm'>
                            <div>Items:</div>
                            <div className='justify-self-end'>${subCost.toFixed(2)}</div>
                            <div>Shipping:</div>
                            <div className='justify-self-end'>$0.00</div>
                        </div>
                        <div className='mx-4 grid grid-cols-2 gap-1 pb-2 mb-2 border-b text-sm'>
                            <div>Before tax:</div>
                            <div className='justify-self-end'>${subCost.toFixed(2)}</div>
                            <div>Estimated HST:</div>
                            <div className='justify-self-end'>${(totalCost - subCost).toFixed(2)}</div>
                        </div>
                        <div className='mx-4 grid grid-cols-2 gap-1 pb-2 mb-2 text-lg text-blue-900'>
                            <div className='font-semibold'>Order Total:</div>
                            <div className='justify-self-end font-bold'>${totalCost.toFixed(2)}</div>
                            <div className='col-span-2 justify-self-end mt-6'>
                                <button className='transition duration-300 ease-in-out bg-blue-200 hover:bg-blue-300
                                rounded-md px-2 py-1 shadow'
                                onClick={() => {placeOrder()}}>
                                    <span className='font-semibold text-lg'>Place Order</span>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </>);
}

export default Checkout;