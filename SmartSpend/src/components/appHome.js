import Splash from '../assets/splash.png';
import Home from '../assets/home.png';
import Login from '../assets/login.png';
import Transfer from '../assets/transfere.png';
import Budget from '../assets/budget.png';
import External from '../assets/external.png';
import Statistics from '../assets/statistics.png';
import Profile from '../assets/profile.png';
import Internal from '../assets/internal.png';
import History from '../assets/history.png';
import Proceed from '../assets/proceed.png';
import { useNavigate } from 'react-router-dom';
import KeyFeatures from '../components/keyFeatures';

export const AppHome = () => {
  const navigate = useNavigate();

  return (
    <div className="w-100">
      <div className='container flex-wrap gap-2 p-2 w-100'>
        {/* Image Section */}
        <div className="p-2 m-2">
          <div className='image-container rounded'>
            <img src={Home} alt='SmartSpend App' className='image'></img>
          </div>
        </div>

        {/* About Section */}
        <div className="p-2 m-2 flex-card">
          <div className="l-hearder mb-4">About SmartSpend</div>
          <p>
            Smart Spend is a mobile budgeting application that empowers users to 
            take control of their finances. It seamlessly integrates with bank accounts, 
            automatically tracks and categorizes transactions, and offers personalized budgeting 
            features and actionable insights. Smart Spend aims to promote financial literacy, 
            encourage responsible spending, and help users achieve their financial goals while avoiding debt.
          </p>

          <button 
            onClick={() => navigate('/about')}
            className="btn btn-success mt-3"
          >
            Get Started
          </button>
          
          {/* Screenshot Gallery */}
          <div className='scroll-container flex-wrap gap-2 p-2 m-2 mt-4 pt-4 shadow-sm'>
            {[
              { img: Splash, label: 'Splash' },
              { img: Proceed, label: 'Proceed' },
              { img: Login, label: 'Login' },
              { img: Home, label: 'Home' },
              { img: History, label: 'History' },
              { img: Statistics, label: 'Statistics' },
              { img: Budget, label: 'Budget' },
              { img: Transfer, label: 'Transfer' },
              { img: External, label: 'External' },
              { img: Internal, label: 'Internal' },
              { img: Profile, label: 'Profile' }
            ].map((item, index) => (
              <div key={index} className='small-image rounded'>
                <img src={item.img} alt={`SmartSpend ${item.label}`} className='image rounded'></img>
                <div className='mb-4'>{item.label}</div>
              </div>
            ))}
          </div>
        </div>
      </div>

      {/* Objectives Section */}
      <div className='bg-light p-4 mt-4 text-start'>
        <div className="s-header mb-2">Objectives</div>
        <p>The primary objective of Smart Spend is to develop a user-friendly mobile application that:</p>
        <ul>
          <li>Empowers individuals to make informed financial decisions</li>
          <li>Promotes responsible spending habits</li>
          <li>Helps users achieve their financial goals and avoid debt</li>
          <li>Enhances financial literacy through intuitive tools and insights</li>
        </ul>
      </div>

      {/* Key Features Component */}
      <KeyFeatures />

        <div className="download-section bg-light p-5 text-center mt-4">
            <h2 className="s-header mb-4">Download SmartSpend</h2>
            <p className="mb-4">Take control of your finances today. Available now on Android, coming soon to iOS.</p>
            
            <div className="d-flex justify-content-center gap-4 flex-wrap">
                {/* Play Store Button */}
                <a 
                href="https://play.google.com/store/apps/details?id=com.yourpackage.name" 
                target="_blank" 
                rel="noopener noreferrer"
                className="download-btn"
                >
                <img 
                    src="https://upload.wikimedia.org/wikipedia/commons/7/78/Google_Play_Store_badge_EN.svg" 
                    alt="Get it on Google Play" 
                    style={{ height: '60px' }}
                />
                </a>
                
                {/* App Store Coming Soon */}
                <div className="coming-soon-btn position-relative">
                <img 
                    src="https://upload.wikimedia.org/wikipedia/commons/3/3c/Download_on_the_App_Store_Badge.svg" 
                    alt="Coming soon on App Store" 
                    style={{ height: '60px', opacity: 0.5 }}
                />
                <div className="coming-soon-label position-absolute top-50 start-50 translate-middle">
                    Coming Soon
                </div>
                </div>
            </div>
        </div>
    </div>
  );
};