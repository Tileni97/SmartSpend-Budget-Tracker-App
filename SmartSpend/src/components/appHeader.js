import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { Link } from 'react-router-dom';

export const AppHeader = () => {
  return (
    <Navbar collapseOnSelect expand="lg" className="bg-light">
      <Container>
        <Navbar.Brand as={Link} to="/" className='text-success s-hearder'>SmartSpend</Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="me-auto"></Nav>
          <Nav>
            <Nav.Link as={Link} to="/features">Features</Nav.Link>
            <Nav.Link as={Link} to="/requirements">Requirements</Nav.Link>
            <Nav.Link as={Link} to="/register" className='bg-success text-light rounded'>Register</Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};