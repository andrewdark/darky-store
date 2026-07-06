import PageTitle from "./PageTitle";
import { useNavigate, Link } from "react-router-dom";
import emptyCartImage from "../assets/util/emptycart.png";
import { useCart } from "../store/cart-context";
import CartTable from './CartTable';

const Cart = () => {
    const { cart } = useCart();
    // Memoize the cart length check to prevent re-renders
    const isCartEmpty = cart.length === 0;

    const navigation = useNavigate();

    const handleClick = () => {
        navigation("/home", { state: { username: "dark" } });
    };

    return (
        <div className=" py-8 bg-normalbg dark:bg-darkbg font-primary">
            <div className="max-w-4xl mx-auto px-4">
                <PageTitle title="Your Cart" />
                {!isCartEmpty ? (
                    <>
                        <CartTable />
                        <div className="flex justify-between mt-8 space-x-4">
                            {/* Back to Products Button */}
                            <Link
                                to="/home"
                                className="py-2 px-4 bg-primary dark:bg-light text-white dark:text-black text-xl font-semibold rounded-sm flex justify-center items-center hover:bg-dark dark:hover:bg-lighter transition"
                            >
                                Back to Products
                            </Link>
                            {/* Proceed to Checkout Button */}
                            <button className="py-2 px-4 bg-primary dark:bg-light text-white dark:text-black text-xl font-semibold rounded-sm flex justify-center items-center hover:bg-dark dark:hover:bg-lighter transition">
                                Proceed to Checkout
                            </button>
                        </div>
                    </>
                ) :
                    (<div className="text-center text-gray-600 dark:text-lighter flex flex-col items-center">
                        <p className="max-w-75 px-2 mx-auto text-base mb-4">
                            Oops... Your cart is empty. Continue shopping
                        </p>
                        <img
                            src={emptyCartImage}
                            alt="Empty Cart"
                            className="max-w-48 mx-auto mb-6 dark:bg-light dark:rounded-md"
                        />
                        <button
                            onClick={handleClick}
                            className="py-2 px-4 bg-primary dark:bg-light text-white dark:text-black text-xl font-semibold rounded-sm flex justify-center items-center hover:bg-dark dark:hover:bg-lighter transition"
                        >
                            Back to Products
                        </button>
                    </div>)
                }
            </div>
        </div>
    );
};

export default Cart;