import * as React from 'react';
import { IItem, IItemInCart, API_ROOT } from '../App';
import { AppContext } from './AppContext';

interface IProps {
   item: IItem
} 

function ItemBox(props: IProps) {

    const thisItem = props.item;
    const {cart, setCart} = React.useContext(AppContext)

    const addItemToCart = () => {
        
        // First perform a check to see if the item is already in the cart
        const itemsInCart: IItemInCart[] = cart.itemsInCart;
        let itemFound: boolean = false;
        let itemInCartQty: number = -1;
        let itemInCartID: number = -1;
        itemsInCart.forEach((itemInCart: IItemInCart) => {
            const item: IItem = itemInCart.item;
            if (item.id == thisItem.id) {
                itemFound = true;
                itemInCartQty = itemInCart.quantity;
                itemInCartID = itemInCart.id;
            }
        });

        // If the item is already in our cart, send a patch request to update quantity
        if (itemFound) {
            const newQuantity = itemInCartQty + 1;
            const cartRequestUrl = API_ROOT + `/cart/${cart.id}/item/${itemInCartID}?quantity=${newQuantity}`;
            fetch(cartRequestUrl, { method: 'PATCH' })
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
            return;
        }

        // If the item isnt in our cart, do a post request
        const cartRequestUrl = API_ROOT + `/cart/${cart.id}/item/${thisItem.id}`;
        fetch(cartRequestUrl, { method: 'POST' })
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
        <div className='ml-2 mr-2 mb-4 mt-4 border shadow-sm'>
            <img src={thisItem.pictureUrl} className='w-[175px] h-[175px] border-b shadow-inner'></img>
            <div className='ml-2 mr-2'>
                <div className='text-xl font-semibold text-blue-900'>{thisItem.name}</div>
                <div className='text-xs'>{thisItem.description}</div>
                <div className='w-full inline-block mt-2'>
                    <div className='float-left'>
                        <div className='text-medium text-lg'>${thisItem.price}</div>
                    </div>
                    <div className='float-right'>
                        <button className='transition duration-300 ease-in-out bg-blue-200 hover:bg-blue-300 
                        px-2 rounded-md leading-6 shadow-sm text-blue-900 font-medium'
                        onClick={() => {addItemToCart()}}>
                            Add
                        </button>
                    </div>
                </div>
                
            </div>
            
        </div>
    )
}

export default ItemBox;