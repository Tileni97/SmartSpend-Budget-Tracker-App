import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { AppHeader } from './components/appHeader';
import { AppHome } from './components/appHome';
import { About } from './components/about'; // You'll need to create this
import { AppFooter } from './components/AppFooter'; // Add this import

function App() {
  return (
    <Router>
      <div className='App'>
        <header>
          <AppHeader />
        </header>
        <main>
          <Routes>
            <Route path="/" element={<AppHome />} />
            <Route path="/about" element={<About />} />

          </Routes>
        </main>
        <footer>
          <AppFooter /> {/* Add the footer here */}
        </footer>
      </div>
    </Router>
  );
}

export default App;