import * as React from 'react';
import { useMemo } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { AppContext } from './components/AppContext';
import Marketplace from './views/Marketplace';
import Checkout from "./views/Checkout";
import ShoppingCart from './components/CartItem';

// Interfaces as expected from the API

interface IItem {
    id: number,
    name: string,
    description: string,
    price: number,
    pictureUrl: string,
    categoryName: string
}
interface IItemInCart {
    id: number,
    quantity: number,
    item: IItem,
    price: number
}
interface ICart {
    id: number,
    userId: number,
    itemsInCart: IItemInCart[],
    length: number,
    subCost: number,
    totalCost: number,
    discount?: number
}
interface ICategory {
    id: number,
    name: string,
    items: IItem[]
}

export {IItem, IItemInCart, ICart, ICategory};

export const API_ROOT: string = "https://shoppinglist301.herokuapp.com/api";

function App() {

    const [items, setItems] = React.useState<IItem[]>([]);
    const [categories, setCategories] = React.useState<ICategory[]>([]);

    const [cart, setCart] = React.useState<ICart>(null);

    // Make the API call to populate the items state
    React.useEffect(() => {

        // Get the items from the API
        const itemRequestUrl = API_ROOT + "/items";
        console.log("Requesting items...");
        fetch(itemRequestUrl)
        .then((response) => {
            response.json()
            .then((parsedJson) => {
                setItems(parsedJson);
                console.log("Items received.");
            })
            .catch(() => {
                console.log("No items were received.");
            });
        });

        // Get the categories from the API
        const categoryRequestUrl = API_ROOT + "/categories";
        console.log("Requesting categories...");
        fetch(categoryRequestUrl)
        .then((response) => {
            response.json()
            .then((parsedJson) => {
                setCategories(parsedJson);
                console.log("Categories received.");
            })
            .catch(() => {
                console.log("No categories were received.");
            });
        });

        // Get the new cart from the API
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
    }, []);

    const providerValue = useMemo(() => (
        {items, setItems,
         categories, setCategories,
         cart, setCart}), 
        [items, setItems,
         categories, setCategories, 
         cart , setCart]
    );

    return (
        <Router>
            <AppContext.Provider value={providerValue}>
                <Routes>
                    <Route path='/' element={<Marketplace/>} />
                    <Route path='/Checkout' element={<Checkout/>} />
                </Routes>
            </AppContext.Provider>
        </Router>
    );
}

export default App;