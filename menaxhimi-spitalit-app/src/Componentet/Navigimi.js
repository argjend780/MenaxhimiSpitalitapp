import React from 'react'
import {Navbar,Nav,Container} from 'react-bootstrap'
import {
    BrowserRouter,
    Routes,
    Route,
    Link
    } from "react-router-dom"
    import Login from './Login';
    import Register from './Register';
    import Doktoret from './Doktorett';
    
function Navigimi() {
  return (
    <BrowserRouter>
    <div>
    <>
    <Navbar bg="dark" data-bs-theme="dark">
      <Container>
        <Navbar.Brand href="#home">Navbar</Navbar.Brand>
        <Nav className="me-auto">
          <Nav.Link as={Link} to="/">Home</Nav.Link>
          <Nav.Link as={Link} to="/login">Login</Nav.Link>
          <Nav.Link as={Link} to="/register">Register</Nav.Link>
          <Nav.Link as = {Link} to="/Doktorett" >doktoret</Nav.Link>
        </Nav>
      </Container>
    </Navbar>
  </>
  <div>
    <Routes>
        <Route path="/login" element={<Login/>}/>
        <Route path="/register" element={<Register/>}/>
        <Route path="/Doktorett" element={<Doktoret/>}/>


    </Routes>
  </div>
  </div>
  </BrowserRouter>
  )
}

export default Navigimi