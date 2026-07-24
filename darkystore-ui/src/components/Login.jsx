import PageTitle from "./PageTitle";
import { useEffect } from "react";
import { Form, Link, useActionData, useNavigate } from "react-router-dom";
import apiClient from "../api/apiClient";
import { toast } from "react-toastify";
import { useDispatch } from "react-redux";
import { loginSuccess } from "../store/auth-slice";

const Login = () => {
    const labelStyle = "block text-lg font-semibold text-primary dark:text-light mb-2";
    const textFieldStyle = "w-full px-4 py-2 text-base border rounded-md transition border-primary dark:border-light focus:ring focus:ring-dark dark:focus:ring-lighter focus:outline-none text-gray-800 dark:text-lighter bg-white dark:bg-gray-600 placeholder-gray-400 dark:placeholder-gray-300";
    const actionData = useActionData();
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const from = sessionStorage.getItem("redirectPath") || "/home";

    useEffect(() => {
        if (actionData?.success) {
            dispatch(loginSuccess({ jwtToken: actionData.jwtToken, user: actionData.user }));
            toast.success("Success!");
            sessionStorage.removeItem("redirectPath");
            setTimeout(() => {
                navigate(from);
            }, 100)
        } else if (actionData?.errors) {
            toast.error(actionData.errors.message || "Login failed.");
        }
    }, [actionData]);

    return (
        <div className="flex items-center justify-center font-primary dark:bg-darkbg">
            <div className="bg-white dark:bg-gray-700 shadow-md rounded-lg max-w-sm 2xl:max-w-md w-full px-8 py-6">
                {/* Title */}
                <PageTitle title="Login" />
                {/* Form */}
                <Form method="POST" className="space-y-4">
                    {/* Email Field */}
                    <div>
                        <label htmlFor="username" className={labelStyle}>
                            Username
                        </label>
                        <input
                            id="username"
                            type="text"
                            name="username"
                            placeholder="Your Username"
                            required
                            className={textFieldStyle}
                        />
                    </div>

                    {/* Password Field */}
                    <div>
                        <label htmlFor="password" className={labelStyle}>
                            Password
                        </label>
                        <input
                            id="password"
                            type="password"
                            name="password"
                            placeholder="Your Password"
                            required
                            minLength={4}
                            maxLength={20}
                            className={textFieldStyle}
                        />
                    </div>

                    {/* Submit Button */}
                    <div className='mt-16'>
                        <button
                            type="submit"
                            className="w-full px-6 py-2 text-white dark:text-black text-xl rounded-md transition duration-200 bg-primary dark:bg-light hover:bg-dark dark:hover:bg-lighter"
                        >
                            Login
                        </button>
                    </div>
                </Form>

                {/* Register Link */}
                <p className="text-center text-gray-600 dark:text-gray-400 mt-4">
                    Don't have an account?{" "}
                    <Link
                        to="/register"
                        className="text-primary dark:text-light hover:text-dark dark:hover:text-primary transition duration-200"
                    >
                        Register Here
                    </Link>
                </p>
            </div>
        </div>
    );
};

export default Login;

export async function loginAction({ request }) {
    const data = await request.formData();

    const loginData = {
        username: data.get("username"),
        password: data.get("password"),
    };

    try {
        const response = await apiClient.post("/auth/login", loginData);
        const { message, user, jwtToken } = response.data;
        return { success: true, message, user, jwtToken };
    } catch (error) {
        if (error.response?.status === 401) {
            return {
                success: false,
                errors: { message: "Invalid username or password" },
            };
        }
        throw new Response(
            error.response?.data?.message ||
            error.message ||
            "Failed to login. Please try again.",
            { status: error.response?.status || 500 }
        );
    }
}