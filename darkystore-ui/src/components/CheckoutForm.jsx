import { useState } from "react";
import { useAuth } from "../store/auth-context";
import apiClient from "../api/apiClient";
import { useCart } from "../store/cart-context";
import { useStripe, useElements, CardNumberElement, CardExpiryElement, CardCvcElement, } from "@stripe/react-stripe-js";
import { useNavigate, useNavigation } from "react-router-dom";
import PageTitle from "./PageTitle";
import { toast } from "react-toastify";

const CheckoutForm = () => {
    const { user } = useAuth();
    const { cart, totalPrice, clearCart } = useCart();
    const stripe = useStripe();
    const elements = useElements();
    const navigate = useNavigate();
    const [isProcessing, setIsProcessing] = useState(false);
    const [errorMessage, setErrorMessage] = useState("");
    const [elementErrors, setElementErrors] = useState({
        cardNumber: "",
        cardExpiry: "",
        cardCvc: "",
    });

    const isDarkMode = localStorage.getItem("theme") === "dark";

    const labelStyle =
        "block text-lg font-semibold text-primary dark:text-light mb-2";
    const fieldBaseClass =
        "w-full px-4 py-2 text-base border rounded-md transition border-primary dark:border-light focus:ring focus:ring-dark dark:focus:ring-lighter focus:outline-none text-gray-800 dark:text-lighter bg-white dark:bg-gray-600 placeholder-gray-400 dark:placeholder-gray-300";
    const fieldErrorClass =
        "border-red-400 dark:border-red-500 focus:ring-red-500";
    const fieldValidClass =
        "border-primary dark:border-light focus:ring-dark dark:focus:ring-lighter";

    const getClassForElement = (field) =>
        `${fieldBaseClass} ${elementErrors[field] ? fieldErrorClass : fieldValidClass
        }`;

    const elementOptions = {
        style: {
            base: {
                fontSize: "16px",
                color: isDarkMode ? "#E5E7EB" : "#374151",
                backgroundColor: isDarkMode ? "#4B5563" : "#FFFFFF",
            },
            invalid: {
                color: "#F87171",
                backgroundColor: isDarkMode ? "#4B5563" : "#FFFFFF",
            },
        },
    };

    function handleCardChange(field, event) {
        setElementErrors((prev) => ({
            ...prev,
            [field]: event.error ? event.error.message : "",
        }));
    }

    //TODO: implement LOGIC
    const handleSubmit = async (event) => {
        event.preventDefault();
        if (!stripe || !elements) {
            setErrorMessage("Stripe.js is not loaded yet.");
            return;
        }
        setIsProcessing(true);

        try {
            const response = null;
            console.log("Sending request ...", response);
        } catch (error) {
            setErrorMessage("Error processing payment. Please try again later.");
            console.error("Error creating PaymentIntent:", error);
        } finally {
            setIsProcessing(false);
        }
    };

    return (
        <div className="flex items-center justify-center font-primary dark:bg-darkbg">
            <div
                className={
                    isProcessing
                        ? "visible  flex flex-col justify-center items-center my-[200px] "
                        : "hidden"
                }
            >
                <p className="mt-4 text-2xl font-normal text-primary dark:text-light">
                    Processing Payment.... Don't refresh the page
                </p>
            </div>
            <div
                className={
                    isProcessing
                        ? "hidden"
                        : "visible bg-white dark:bg-gray-700 shadow-md rounded-lg max-w-md w-full px-8 py-6"
                }
            >
                <PageTitle title="Complete Your Payment" />

                <p className="text-center mt-8 text-lg text-gray-600 dark:text-lighter mb-8">
                    Amount to be charged: <strong>${totalPrice.toFixed(2)}</strong>
                </p>

                <form onSubmit={handleSubmit} className="space-y-6">
                    {errorMessage && (
                        <div className="text-red-500 text-sm text-center">
                            {errorMessage}
                        </div>
                    )}
                    {/* Card Number */}
                    <div>
                        <label htmlFor="cardNumber" className={labelStyle}>
                            Card Number
                        </label>
                        <div id="cardNumber" className={getClassForElement("cardNumber")}>
                            <CardNumberElement
                                options={elementOptions}
                                onChange={(event) => handleCardChange("cardNumber", event)}
                            />
                        </div>
                        {elementErrors.cardNumber && (
                            <p className="text-red-500 text-sm mt-1">
                                {elementErrors.cardNumber}
                            </p>
                        )}
                    </div>

                    {/* Card Expiry */}
                    <div>
                        <label htmlFor="cardExpiry" className={labelStyle}>
                            Expiry Date
                        </label>
                        <div id="cardExpiry" className={getClassForElement("cardExpiry")}>
                            <CardExpiryElement
                                options={elementOptions}
                                onChange={(event) => handleCardChange("cardExpiry", event)}
                            />
                        </div>
                        {elementErrors.cardExpiry && (
                            <p className="text-red-500 text-sm mt-1">
                                {elementErrors.cardExpiry}
                            </p>
                        )}
                    </div>

                    {/* Card CVC */}
                    <div>
                        <label htmlFor="cardCvc" className={labelStyle}>
                            CVC
                        </label>
                        <div id="cardCvc" className={getClassForElement("cardCvc")}>
                            <CardCvcElement
                                options={elementOptions}
                                onChange={(event) => handleCardChange("cardCvc", event)}
                            />
                        </div>
                        {elementErrors.cardCvc && (
                            <p className="text-red-500 text-sm mt-1">
                                {elementErrors.cardCvc}
                            </p>
                        )}
                    </div>

                    {/* Submit Button */}
                    <div>
                        <button
                            type="submit"
                            disabled={!stripe || isProcessing}
                            className="w-full px-6 py-2 mt-6 text-white dark:text-black text-xl bg-primary dark:bg-light hover:bg-dark dark:hover:bg-lighter rounded-md transition duration-200"
                        >
                            {isProcessing ? "Payment processing..." : "Pay Now"}
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default CheckoutForm;