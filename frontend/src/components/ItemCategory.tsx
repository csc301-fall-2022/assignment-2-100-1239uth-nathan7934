import clsx from 'clsx';
import * as React from 'react';
import { ReactElement } from 'react';
import AnimateHeight from 'react-animate-height';
import { IItem } from '../App';
import ItemBox from './ItemBox';

interface IProps {
    name: string
    items: IItem[]
}

function ItemCategory(props: IProps) {

    const {name, items} = props;

    const [expanded, setExpanded] = React.useState<boolean>(true);
    const [height, setHeight] = React.useState<string | number>('auto');

    const renderItems = () => {
        const itemsList: ReactElement[] = [];
        items.forEach((item: IItem, index: number) => {
            itemsList.push(
                <ItemBox item={item} key={index}/>
            );
        });
        return(<>{itemsList}</>);
    }

    return(
        <div className=''>
            <div className='flex relative w-full h-12 bg-blue-100 rounded-md inline-block border border-gray-200'>
                <div className='relative left-3 top-2 text-xl font-medium text-blue-900'>{name}</div>
                <button className='absolute right-4 top-[4px] text-3xl text-blue-900 font-semibold font-mono hover:text-blue-600'
                onClick={() => {
                    setExpanded(!expanded);
                    setHeight(height == 0 ? 'auto' : 0);
                }}>
                    {expanded ? '-' : '+'}
                </button>
            </div>
            <AnimateHeight duration={750} height={height} easing='ease-in-out'
            className='overflow-hidden rounded-b-md border-b border-x shadow-sm'>
                <div className='flex w-full inline-block justify-center'>
                    {renderItems()}
                </div>
            </AnimateHeight>
        </div>
        
    );
}

export default ItemCategory;