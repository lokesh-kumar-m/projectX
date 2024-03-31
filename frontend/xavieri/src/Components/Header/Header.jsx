
import { useNavigate,Link } from "react-router-dom";
import { useAuth } from "../auth";
import "./header.css"
const Header= () =>{
    const authContext=useAuth()
    const navigateTo=useNavigate()

    function handlelogout(){
        authContext.islogout()
        navigateTo(`/users/login`)
    }
    return(
        <header className="header">
        <nav className="nav container">
          <Link to={`/welcome/${authContext.username}`} className="nav-logo">Bill</Link>
          
          <div className="nav-menu">
            <ul className="nav-list">
              <li className="nav-item">
                <a href={`/welcome/${authContext.username}`} className='nav-link active-link'>
                  {authContext.isAuthentic?authContext.username:"Home"}
                </a>
              </li>

              <li className="nav-item">
                {authContext.isAuthentic?<button className="btn"onClick={handlelogout}>Logout</button>:<button className="btn" onClick={()=>navigateTo(`/users/login`)}>Login</button>}
                
              </li>
            </ul>
          </div>
          
        </nav>
      </header>
    )
}
export default Header;