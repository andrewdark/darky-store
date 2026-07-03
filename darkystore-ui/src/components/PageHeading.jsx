import PageTitle from './PageTitle';

const PageHeading = (props) => {
    const { title, children } = props;

    return (
        <div className="text-center max-w-xl mx-auto px-4 py-6">
            <PageTitle title={title} />
            <p className="font-primary leading-6 text-gray-600 dark:text-lighter">
                {children}
            </p>
        </div>
    );
};

export default PageHeading;