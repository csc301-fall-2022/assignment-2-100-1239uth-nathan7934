import * as React from 'react';
import { IItem } from '../App';

interface IProps {
   item: IItem
} 

function ItemBox(props: IProps) {
    const item = props.item;

    return (
        <div className='ml-2 mr-2 mb-4 mt-4 border shadow-sm'>
            <img src={item.pictureUrl} className='w-[175px] h-[175px] border-b shadow-inner'></img>
            <div className='ml-2 mr-2'>
                <div className='text-xl font-semibold text-blue-900'>{item.name}</div>
                <div className='text-xs'>{item.description}</div>
                <div className='w-full inline-block mt-2'>
                    <div className='float-left'>
                        <div className='text-medium text-lg'>${item.price}</div>
                    </div>
                    <div className='float-right'>
                        <button className='transition duration-300 ease-in-out bg-blue-200 hover:bg-blue-300 
                        px-2 rounded-md leading-6 shadow-sm text-blue-900 font-medium'>
                            Add
                        </button>
                    </div>
                </div>
                
            </div>
            
        </div>
    )
}

export default ItemBox;