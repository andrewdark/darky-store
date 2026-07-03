import PageTitle from "./PageTitle";
import { Form } from "react-router-dom";
import apiClient from "../api/apiClient";
import { useActionData, useNavigation, useSubmit } from "react-router-dom";
import { useEffect, useRef } from "react";
import { toast } from "react-toastify";


const Contact = () => {

    const formRef = useRef(null);
    const actionData = useActionData(); //те що ми return з нашої функції action (string, obj, null, any, що хоч)
    const submit = useSubmit(); // не обовєязково, але беремо під контроль form submission. 
    const navigation = useNavigation();
    const isSubmitting = navigation.state === "submitting";

    useEffect(() => {
        if (actionData?.success) {
            console.log()
            formRef.current?.reset(); //ref us to the real dom, not to virtual. Doesnt refresh page 
            toast.success("Your message has been submitted successfully! " + actionData.id);
        }
    }, [actionData]);

    const handleSubmit = (event) => {

        event.preventDefault();

        const userConfirmed = window.confirm(
            "Are you sure you want to submit the form?"
        );

        if (userConfirmed) {
            const formData = new FormData(formRef.current); // Get form data from ref , not from event
            submit(formData, { method: "post" }); // Proceed with form submission manualy
        } else {
            toast.info("Охрана отмєна");
        }

        //event.target.reset(); // Очищає всі поля форми
    }

    const labelStyle = "block text-lg font-semibold text-primary dark:text-light mb-1";
    const textFieldStyle = "w-full px-4 py-2 text-base border rounded-md transition border-primary dark:border-light focus:ring focus:ring-dark dark:focus:ring-lighter focus:outline-none text-gray-800 dark:text-lighter bg-white dark:bg-gray-600 placeholder-gray-400 dark:placeholder-gray-300";

    return (
        <div className="max-w-6xl mx-auto px-6 py-4 font-primary bg-normalbg dark:bg-darkbg">
            {/* Page Title */}
            <PageTitle title="Contact Us" />
            {/* Contact Info */}
            <p className="max-w-3xl mx-auto text-gray-600 dark:text-lighter mb-4 text-center">
                If you have any questions, feedback, or suggestions, please don’t hesitate to reach out.
            </p>

            {/* Contact Form */}
            <Form method="POST" onSubmit={handleSubmit} className="space-y-6 max-w-3xl mx-auto" ref={formRef}>

                {/* Name Field */}
                <div>
                    <label htmlFor="name" className={labelStyle}>
                        Name
                    </label>
                    <input id="name" name="name" type="text" placeholder="Your Name" className={textFieldStyle} required
                        minLength={4} maxLength={30} />
                    {actionData?.errors?.name && (
                        <p className="text-red-500 text-sm mt-1">
                            {actionData.errors.name}
                        </p>
                    )}
                </div>

                {/* Email and mobile Row */}
                <div className="grid grid-cols-1 sm:grid-cols-2 gap-6">
                    {/* Email Field */}
                    <div>
                        <label htmlFor="email" className={labelStyle}>Email</label>
                        <input id="email" name="email" type="email" placeholder="Your Email" className={textFieldStyle} required />
                        {actionData?.errors?.email && (
                            <p className="text-red-500 text-sm mt-1">
                                {actionData.errors.email}
                            </p>
                        )}
                    </div>

                    {/* Mobile Field */}
                    <div>
                        <label htmlFor="mobileNumber" className={labelStyle}>Mobile Number</label>
                        <input id="mobileNumber" name="mobileNumber" type="tel" required pattern="^\d{10}$"
                            title="Mobile number must be exactly 10 digits" placeholder="Your Mobile Number"
                            className={textFieldStyle}
                        />
                        {actionData?.errors?.mobileNumber && (
                            <p className="text-red-500 text-sm mt-1">
                                {actionData.errors.mobileNumber}
                            </p>
                        )}
                    </div>
                </div>

                {/* Message Field */}
                <div>
                    <label htmlFor="message" className={labelStyle}>Message</label>
                    <textarea id="message" name="message" rows="4" placeholder="Your Message" className={textFieldStyle} required minLength={5} maxLength={500}></textarea>
                    {actionData?.errors?.message && (
                        <p className="text-red-500 text-sm mt-1">
                            {actionData.errors.message}
                        </p>
                    )}
                </div>
                {/* Submit Button */}
                <div className="text-center">
                    <button type="submit" disabled={isSubmitting}
                        className="px-6 py-2 text-white dark:text-black text-xl rounded-md transition duration-200 bg-primary dark:bg-light hover:bg-dark dark:hover:bg-lighter">
                        {isSubmitting ? "Submitting..." : "Submit"}
                    </button>
                </div>
            </Form>
        </div>
    );
};

export default Contact;

//--------------------------------------------//
//-----=====>>>>> PAGE ACTION <<<<<=====-----//
//------------------------------------------//
export async function contactAction({ request, params }) {
    try {
        const data = await request.formData();

        // const contactData = {
        //     name: data.get("name"),
        //     email: data.get("email"),
        //     mobileNumber: data.get("mobileNumber"),
        //     message: data.get("message"),
        // };
        const plainObject = Object.fromEntries(data);

        const response = await apiClient.post("/contacts", plainObject);
        return { success: true, id: response.data.contactId };
        // return redirect("/home");
    } catch (error) {
        if (error.response?.status === 400) {
            return { success: false, errors: error.response?.data };
        }
        throw new Response(
            error.response?.data?.error ||
            error.message || "Failed to submit your data. Please try again.",
            { status: error.status || 500 }
        );
    }
}