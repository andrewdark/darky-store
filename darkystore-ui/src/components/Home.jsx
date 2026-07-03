import PageHeading from './PageHeading';
import ProductListings from './ProductListings';
import apiClient from '../api/apiClient';
import { useLoaderData, useLocation } from "react-router-dom";

const Home = () => {
    const products = useLoaderData();
    const loca = useLocation();
    return (

        <div className='max-w-6xl mx-auto px-6 py-4'>
            <PageHeading title="Explore Dark's Stickers!">
                Add a touch of creativity to your space with our wide range of fun and
                unique stickers. Perfect for any occasion!
            </PageHeading>
            <ProductListings products={products} />
        </div>

    );
};

export default Home;

//--------------------------------------------//
//-----=====>>>>> PAGE LOADER <<<<<=====-----//
//------------------------------------------//
export async function productsLoader() {
    try {
        const response = await apiClient.get("/products", {
            headers: {
                "Authorization": "Bearer xpm-rtx-pop-hop-33i",
                "Content-Type": "application/json"
            }
        }); // Axios GET Request

        return response.data;
    } catch (error) {

        throw new Response(
            error.message || "Failed to fetch products. Please try again.",
            { status: error.status || 500 }
        );
    }
}