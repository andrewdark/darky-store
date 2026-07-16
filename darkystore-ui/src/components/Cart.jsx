import PageTitle from "./PageTitle";
import { useNavigate, Link } from "react-router-dom";
import { useMemo } from 'react';
import emptyCartImage from "../assets/util/emptycart.png";
import { useCart } from "../store/cart-context";
import { useAuth } from "../store/auth-context";
import CartTable from './CartTable';

const Cart = () => {
    const { cart } = useCart();
    const { isAuthenticated, user } = useAuth();
    // Memoize the cart length check to prevent re-renders

    const isCartEmpty = cart.length === 0;

    const isAddressIncomplete = useMemo(() => {
        if (!isAuthenticated) return false;
        if (!user.address) return true;
        const { street, city, state, postalCode, country } = user.address;
        return !street || !city || !state || !postalCode || !country;
    }, [isAuthenticated, user]);

    const navigation = useNavigate();

    const handleClick = () => {
        navigation("/home", { state: { username: user.username ?? "dark" } });
    };

    return (
        <div className=" py-8 bg-normalbg dark:bg-darkbg font-primary">
            <div className="max-w-4xl mx-auto px-4">
                <PageTitle title="Your Cart" />
                {!isCartEmpty ? (
                    <>
                        {isAddressIncomplete && (
                            <p className="text-red-500 text-lg mt-2 text-center">
                                Please update your address in your profile to proceed to
                                checkout.
                            </p>
                        )}
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
                            <Link
                                to={isAddressIncomplete ? "#" : "/checkout"}
                                className={`py-2 px-4 text-xl font-semibold rounded-sm flex justify-center items-center transition
                                    ${isAddressIncomplete
                                        ? "bg-gray-400 cursor-not-allowed"
                                        : "bg-primary dark:bg-light hover:bg-dark dark:hover:bg-lighter"
                                    } text-white dark:text-black`}
                                onClick={(e) => {
                                    if (isAddressIncomplete) {
                                        e.preventDefault();
                                    }
                                }}
                            >
                                Proceed to Checkout
                            </Link>
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