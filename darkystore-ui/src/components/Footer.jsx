import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHeart } from "@fortawesome/free-solid-svg-icons";

const Footer = () => {
    return (
        <footer className="flex justify-center items-center py-4 font-primary text-gray-700 dark:text-gray-300">
            Built with
            <FontAwesomeIcon
                icon={faHeart}
                className="text-red-600 mx-1 animate-pulse"
                aria-hidden="true"
            />
            by
            <a href="http://darknsoft.pp.ua/" target="_blank" rel="noreferrer">
                <span className="text-primary dark:text-light font-semibold px-1 transition-colors duration-300 hover:text-dark dark:hover:text-lighter">dark'n'soft</span> company
            </a>
        </footer>
    );
};

export default Footer;