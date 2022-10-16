import * as React from 'react';
import { Link } from 'react-router-dom';


function Checkout() {
    return(<>
        <Link to="/">Back to shopping</Link>
        <div className='flex w-screen justify-center flex-wrap'>
            <div className='w-5/6 lg:w-2/3 xl:w-1/2 min-w-[580px]'>
                <div className='flex w-full mt-10 mb-14 justify-center text-3xl font-sans font-semibold'>
                    Review Cart and Checkout
                </div>
                <div className='w-full'>
                    <div className='flex float-left'>
                        <div>Your Cart</div>
                    </div>
                    <div className='flex float-right'>
                        <div>right test</div>
                    </div>
                </div>
            </div>
        </div>
    </>);
}

export default Checkout;