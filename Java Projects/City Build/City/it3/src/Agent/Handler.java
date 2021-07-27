package Agent;

import java.io.IOException;
import java.util.List;

import NLP.Recognizer;
import Request.Request;

public class Handler {
	AgentHelper helper=new AgentHelper();
	
	private Request req=new Request();
	public Handler(String txt) throws ClassNotFoundException, IOException {

		Recognizer r=new Recognizer(txt);
		List<String>l=r.getNoun();
		List<String>list=r.getVerb();
		
		String[] st=helper.getNounShop();
			for(int i=0;i<st.length;i++) {
				if(l.contains(st[i])){
					String[] st1=helper.getVbShop();
					for(int j=0;j<st1.length;j++) {
							if(list.contains(st1[j])){
							this.req=new Request("PUT","Agent");
							this.req.setPort(4000);
				}
			}
		}
	}
			st=helper.getNounHouse();
			for(int i=0;i<st.length;i++) {
				if(l.contains(st[i])){
					String[] st1=helper.getVbHouse();
					for(int j=0;j<st1.length;j++) {
							if(list.contains(st1[j])){
							this.req=new Request("PUT","Agent");
							this.req.setPort(6000);
				}
			}
		}
	}	
			st=helper.getNounHydro();
			for(int i=0;i<st.length;i++) {
				if(l.contains(st[i])){
					String[] st1=helper.getVbHydro();
					for(int j=0;j<st1.length;j++) {
							if(list.contains(st1[j])){
							this.req=new Request("PUT","Agent");
							this.req.setPort(2000);
				}
			}
		}
	}	
			st=helper.getNounGround();
			for(int i=0;i<st.length;i++) {
				if(l.contains(st[i])){
					String[] st1=helper.getVbGround();
					for(int j=0;j<st1.length;j++) {
							if(list.contains(st1[j])){
							this.req=new Request("PUT","Agent");
							this.req.setPort(1000);
				}
			}
		}
	}	
			
			
	}
	public Request getRequest() {
		return req;
	}
	public void setRequest(Request r) {
		this.req = r;
	}
}
