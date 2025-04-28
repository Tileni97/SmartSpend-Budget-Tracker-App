import { Container, Row, Col } from 'react-bootstrap';
import { FaFacebook, FaTwitter, FaInstagram, FaLinkedin } from 'react-icons/fa';

export const AppFooter = () => {
  return (
    <footer className="bg-dark text-light pt-5 pb-3 mt-5">
      <Container>
        <Row>
          <Col md={4} className="mb-4">
            <h5 className="text-success">SmartSpend</h5>
            <p className="mt-3">
              Take control of your finances with our intuitive budgeting app. 
              Track expenses, save money, and achieve your financial goals.
            </p>
            <div className="social-icons mt-3">
              <a href="#" className="text-light me-3"><FaFacebook size={20} /></a>
              <a href="#" className="text-light me-3"><FaTwitter size={20} /></a>
              <a href="#" className="text-light me-3"><FaInstagram size={20} /></a>
              <a href="#" className="text-light"><FaLinkedin size={20} /></a>
            </div>
          </Col>

          <Col md={2} className="mb-4">
            <h5 className="text-success">Quick Links</h5>
            <ul className="list-unstyled">
              <li className="mb-2"><a href="#" className="text-light">Home</a></li>
              <li className="mb-2"><a href="#" className="text-light">Features</a></li>
              <li className="mb-2"><a href="#" className="text-light">About Us</a></li>
              <li className="mb-2"><a href="#" className="text-light">Contact</a></li>
            </ul>
          </Col>

          <Col md={3} className="mb-4">
            <h5 className="text-success">Support</h5>
            <ul className="list-unstyled">
              <li className="mb-2"><a href="#" className="text-light">FAQ</a></li>
              <li className="mb-2"><a href="#" className="text-light">Help Center</a></li>
              <li className="mb-2"><a href="#" className="text-light">Privacy Policy</a></li>
              <li className="mb-2"><a href="#" className="text-light">Terms of Service</a></li>
            </ul>
          </Col>

          <Col md={3}>
            <h5 className="text-success">Contact Us</h5>
            <ul className="list-unstyled">
              <li className="mb-2">
                <i className="bi bi-envelope me-2"></i> support@smartspend.com
              </li>
              <li className="mb-2">
                <i className="bi bi-telephone me-2"></i> +1 (555) 123-4567
              </li>
              <li className="mb-2">
                <i className="bi bi-geo-alt me-2"></i> 123 Finance St, Moneyville
              </li>
            </ul>
          </Col>
        </Row>

        <hr className="mt-4 mb-4" />

        <Row>
          <Col className="text-center">
            <p className="mb-0">
              &copy; {new Date().getFullYear()} SmartSpend. All rights reserved.
            </p>
          </Col>
        </Row>
      </Container>
    </footer>
  );
};