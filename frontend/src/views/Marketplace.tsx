import * as React from 'react';
import {ReactElement} from 'react';
import {Link} from 'react-router-dom';
import {ICart, ICategory} from '../App';
import ItemCategory from '../components/ItemCategory';

interface MarketplaceProps {
    categories: ICategory[],
    cart: ICart,
    setCart: React.Dispatch<React.SetStateAction<ICart>>
}

function Marketplace(props: MarketplaceProps) {

    const {categories, cart, setCart} = props;

    const renderCategories = () => {
        const categoryList: ReactElement[] = [];
        categories.forEach((category: ICategory, index: number) => {
            categoryList.push(
                <ItemCategory name={category.name} items={category.items} key={index} cart={cart} setCart={setCart}/>
            );
        });
        return (<>{categoryList}</>);
    }

    const showCartSize = () => {
        if (cart != null) {
            return (<>{`(${cart.length})`}</>);
        }
        return (<>{'0'}</>)
    }

    const showCartTotal = () => {
        if (cart != null) {
            const total = cart.subCost;
            return (<>{total.toFixed(2)}</>)
        }
        return (<>0.00</>)
    }

    return (
        <div className='flex w-screen h-screen justify-center flex-wrap'>
            <div className='w-5/6 lg:w-2/3 xl:w-1/2 min-w-[580px]'>
                <div className="flex w-full mt-10 mb-14 justify-center text-5xl font-sans font-semibold">
                    Welcome to the Emporium!
                </div>
                <div className='w-full mb-2 inline-block'>
                    <div className='relative bottom-[2px] float-left'>
                        <span className='text-lg font-medium text-blue-900'>Cart Total:</span>
                        <span className='ml-2 text-2xl font-semibold'>${showCartTotal()}</span>
                    </div>
                    <div className='float-right'>
                        <Link to='/Checkout'>
                            <button className='transition duration-300 ease-in-out bg-blue-200 hover:bg-blue-300
                        rounded-md px-2 py-1 shadow'>
                                <span className='font-medium text-blue-800'>View Cart</span>
                                <span className='ml-1 font-semibold'>{showCartSize()}</span>
                            </button>
                        </Link>
                    </div>
                </div>
                {renderCategories()}
            </div>
        </div>
    );
}

export default Marketplace;