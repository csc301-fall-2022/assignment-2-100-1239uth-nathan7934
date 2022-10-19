import * as React from 'react';
import {API_ROOT, ICart, IItem, IItemInCart} from '../App';

interface Props {
    itemInCart: IItemInCart
    cart: ICart
    setCart: React.Dispatch<React.SetStateAction<ICart>>
}

function CartItem(props: Props) {
    const id: number = props.itemInCart.id;
    const quantity: number = props.itemInCart.quantity;
    const item: IItem = props.itemInCart.item;

    const {cart, setCart} = props;

    const changeQuantity = (increment: boolean) => {
        if (!increment && quantity <= 1) {
            return;
        }

        const newQuantity = increment ? quantity + 1 : quantity - 1;

        const cartRequestUrl = API_ROOT + `/cart/${cart.id}/item/${id}?quantity=${newQuantity}`;
        fetch(cartRequestUrl, {method: 'PATCH'})
            .then((response) => {
                response.json()
                    .then((parsedJson) => {
                        setCart(parsedJson);
                        console.log("Updated cart received.");
                    })
                    .catch(() => {
                        console.log("Something went wrong.");
                    });
            });
    }

    const deleteItem = () => {
        const cartRequestUrl = API_ROOT + `/cart/${cart.id}/item/${id}`;
        fetch(cartRequestUrl, {method: 'DELETE'})
            .then((response) => {
                response.json()
                    .then((parsedJson) => {
                        setCart(parsedJson);
                        console.log("Updated cart received.");
                    })
                    .catch(() => {
                        console.log("Something went wrong.");
                    });
            });
    }

    return (
        <div className='flex mx-4 pb-2 mb-2 inline-block border-b'>
            <div className='flex w-full'>
                <img src={item.pictureUrl} className='w-[75px] h-[75px] shadow'></img>
                <div className='ml-2 w-full'>
                    <div className='text-xl font-medium text-blue-900'>{item.name}</div>
                    <div className=''>{item.description}</div>
                    <div className='h-6 relative'>
                        <button className='text-xs text-gray-400 hover:text-red-600 absolute bottom-0'
                                onClick={() => {
                                    deleteItem()
                                }}>
                            {'[delete]'}
                        </button>
                    </div>
                </div>
                <div className='w-full'>
                    <div className='float-right mt-5 flex inline-block'>
                        <span className='text-lg font-medium relative top-[2px]'>${item.price}</span>
                        <div className="flex flex-row h-8 w-[65px] rounded-lg relative bg-transparent ml-3">
                            <button className="bg-blue-100 text-gray-600 hover:text-gray-700 hover:bg-gray-400 h-full w-20 
                            rounded-l cursor-pointer outline-none"
                                    onClick={() => {
                                        changeQuantity(false)
                                    }}>
                                <span className="m-auto text-2xl font-semibold relative bottom-[2px]">−</span>
                            </button>
                            <div className="outline-none focus:outline-none text-center w-full bg-blue-100 font-semibold 
                            text-md hover:text-black focus:text-black  md:text-basecursor-default flex items-center 
                            text-gray-700 outline-none justify-center">{quantity}</div>
                            <button className="bg-blue-100 text-gray-600 hover:text-gray-700 hover:bg-gray-400 h-full w-20 
                            rounded-r cursor-pointer"
                                    onClick={() => {
                                        changeQuantity(true)
                                    }}>
                                <span className="m-auto text-2xl font-semibold relative bottom-[2px]">+</span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default CartItem;