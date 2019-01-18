package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginCheck
 */
@WebFilter(urlPatterns={"/AccountDelete","/AccountDeleteComp","/Auction","/AuctionEnd","/Bid","/BidComp","/BidList"
		,"/BidSuccessList","/CreateExhibit","/ExhibitDelete","/ExhibitDeleteComp","/ExhibitEdit","/ExhibitEditComp","/ExhibitList"
		,"/History","/Home","/MaypageEdit","/Notice","/Question","/QuestionComp","/tradeContact"
		,"/AccountInfoAdmin","/AccountListAdmin","/AuctionAdmin","/AuctionEndAdmin","/BitSuccessListAdmin","/ExhibitListAdmin"
		,"/HomeAdmin","/NoticeSendAdmin","/QuestionInfoAdmin","/QuestionListAdmin"}) //複数の場合は urlPatterns={"/?????","/?????"}   全部の場合 WebFilter("/*")
public class LoginCheck implements Filter {

    /**
     * Default constructor.
     */
    public LoginCheck() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();

		if(session == null){
			((HttpServletResponse)response).sendRedirect("/kenta/Login.jsp");
			return;
		}else if(session.getAttribute("account") == null){
			((HttpServletResponse)response).sendRedirect("/kenta/Login.jsp");
			return;
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
