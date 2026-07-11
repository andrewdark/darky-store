import { createRoot } from 'react-dom/client'
import './index.css'
import { createBrowserRouter, RouterProvider, createRoutesFromElements, Route, } from "react-router-dom";
import { ToastContainer, Bounce } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

import App from './App.jsx'
import Home from "./components/Home.jsx";
import About from "./components/About.jsx";
import Contact from "./components/Contact.jsx";
import Login from "./components/Login.jsx";
import Register from './components/Register.jsx';
import Cart from "./components/Cart.jsx";
import CheckoutForm from "./components/CheckoutForm.jsx"
import ProductDetail from "./components/ProductDetail.jsx";
import ErrorPage from "./components/ErrorPage.jsx";
import CartProvider from "./store/CartProvider.jsx";
import AuthProvider from "./store/AuthProvider.jsx";
import ProtectedRoute from './components/ProtectedRoute.jsx';
import Profile from './components/Profile.jsx';
import Orders from './components/Orders.jsx';
import { Messages, AdminOrders } from './components/admin'

import { productsLoader } from "./components/Home.jsx";
import { profileLoader, profileAction } from "./components/Profile.jsx";
import { contactAction } from "./components/Contact.jsx";
import { loginAction } from "./components/Login.jsx";
import { registerAction } from "./components/Register.jsx";

const routeDefinitions = createRoutesFromElements(
  <Route path="/" element={<App />} errorElement={<ErrorPage />}>
    <Route index element={<Home />} loader={productsLoader} />
    <Route path="/home" element={<Home />} loader={productsLoader} />
    <Route path="/about" element={<About />} />
    <Route path="/contact" element={<Contact />} action={contactAction} />
    <Route path="/login" element={<Login />} action={loginAction} />
    <Route path="/register" element={<Register />} action={registerAction} />
    <Route path="/cart" element={<Cart />} />
    <Route path="/products/:productId" element={<ProductDetail />} />
    <Route element={<ProtectedRoute />}>
      <Route path="/checkout" element={<CheckoutForm />} />
      <Route path="/profile" element={<Profile />} loader={profileLoader} action={profileAction} />
      <Route path="/orders" element={<Orders />} />
      <Route path="/admin/orders" element={<AdminOrders />} />
      <Route path="/admin/messages" element={<Messages />} />
    </Route>
  </Route>
);

const appRouter = createBrowserRouter(routeDefinitions);

createRoot(document.getElementById('root')).render(
  <>
    <AuthProvider>
      <CartProvider>
        <RouterProvider router={appRouter} />
      </CartProvider>
    </AuthProvider>

    <ToastContainer position='top-center' theme={localStorage.getItem("theme") === "dark" ? "dark" : "light"} transition={Bounce} />
  </>

)
