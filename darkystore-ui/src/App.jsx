import Header from './components/Header'
import Footer from './components/Footer'
import { Outlet, useNavigation } from "react-router-dom";

function App() {
  const navigation = useNavigation();

  return (
    <div className='grid grid-rows-[auto_1fr_auto] min-h-dvh'>
      <Header />

      {navigation.state === "loading" ? (
        <div className="flex items-center justify-center">
          <span className="text-4xl font-semibold text-primary dark:text-light">
            Loading that shit...
          </span>
        </div>
      ) : (
        <Outlet />
      )}

      <Footer />
    </div>
  )
}

export default App
