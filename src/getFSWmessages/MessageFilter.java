package getFSWmessages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MessageFilter {
	public List<String> GetReg(){
		List<String> reg = new ArrayList<String>();
		LinuxCMD cmd = new LinuxCMD();
		List<String> msg;
		try {
			msg = cmd.runCommand(cmd.cmd_reg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		if(msg.size()>3)
		{
			for(String linemsg:msg){
				if(linemsg.startsWith("Contact:")){
					String tmp = linemsg.substring(
							linemsg.indexOf("<")+1, 
							linemsg.length()-1);
					if(tmp.indexOf(";")>=0)
					{
						tmp = tmp.substring(0, tmp.indexOf(";"));
					}
					reg.add(tmp);
				}
			}
		}else{
			return null;
		}
		for(String a:reg){
			System.out.println(a);
		}
		return reg;
	}
	
	public List<String> GetStatus(){
		LinuxCMD cmd = new LinuxCMD();
		try {
			return cmd.runCommand(cmd.cmd_status);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Content> GetConfs(){
		List<Content> contents = new ArrayList<Content>();
		LinuxCMD cmd = new LinuxCMD();
		List<String> msg;
		try {
			msg = cmd.runCommand(cmd.cmd_conf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		if(msg.size()>2){
			int n=0;
			for(String linemsg:msg.subList(1, msg.size()-1)){
				if(!linemsg.startsWith("Conference")){
					n++;
					if(msg.indexOf(linemsg)==(msg.size()-2)){
						contents.add(this.ParseCfLinemsg(msg.subList(msg.indexOf(linemsg)-n, msg.indexOf(linemsg)+1)));
					}
				}else{
					contents.add(this.ParseCfLinemsg(msg.subList(msg.indexOf(linemsg)-n-1, msg.indexOf(linemsg))));
					n=0;
				}
			}
		}else{
			return null;
		}
		return contents;
	}
	
	public List<Content> GetChannels(){
		List<Content> contents = new ArrayList<Content>();
		LinuxCMD cmd = new LinuxCMD();
		List<String> msg;
		try {
			msg = cmd.runCommand(cmd.cmd_channels);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		//如果msg长度等于3则channel数量为0,否则数量为msg.size()-3
		if(msg.size()>3){
			for(String linemsg:msg.subList(1, msg.size()-3)){
				contents.add(this.ParseCHLinemsg(linemsg));
				System.out.println("//");
			}
		}else{
			return null;
		}
		return contents;
	}
	
	protected Content ParseCHLinemsg(String msg){
		Content content = null;
		String[] tmplist = msg.split(",", 13);
		
		if(tmplist[1].endsWith("outbound")){
			content = new Content
					(tmplist[4].substring(tmplist[4].lastIndexOf("/")+1),
					tmplist[1],
					tmplist[2],
					tmplist[7]+"@"+tmplist[8],
					tmplist[9],
					tmplist[10],
					tmplist[11]);
		}else{
			content = new Content
					(tmplist[6]+"@"+tmplist[8],
					tmplist[1],
					tmplist[2],
					tmplist[7]+"@"+tmplist[8],
					tmplist[9],
					tmplist[10],
					tmplist[11]);
		}
		
		System.out.println(content.getRegname());
		System.out.println(content.getDirection());
		System.out.println(content.getActivebgtime());
		System.out.println(content.getCaller());
		System.out.println(content.getDest());
		System.out.println(content.getApplication());
		System.out.println(content.getApplicationdata());
		
		System.out.println(content.getDirection()=="inbound"?"a":"b");
		return content;
	}

	protected Content ParseCfLinemsg(List<String> msg){
		Content content = null;
		List<String> members = new ArrayList<>();
		for(String tmp:msg){
			if(tmp.startsWith("Conference")){
				String timecmd = tmp.substring(0, 
						tmp.indexOf("("));
				LinuxCMD cmd = new LinuxCMD();
				try {
					String msgtime = cmd.runCommand(timecmd+cmd.cmd_conftime).toString();
					String time = msgtime.substring(1, msgtime.length()-1);
					long minute = (long) Math.floor(Integer.parseInt(time)/60);
					int second = Integer.parseInt(time)%60;
					members.add(String.valueOf(minute));
					members.add(String.valueOf(second));
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					members.add("0");
					members.add("0");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					members.add("0");
					members.add("0");
				}
			}else{
				String[] tmplist = tmp.split(";", 3);
				String member = tmplist[1].substring(
						tmplist[1].lastIndexOf("/")+1, 
						tmplist[1].lastIndexOf("@"));
				members.add(member);
			}
		}
		String confname = msg.get(0).substring(11, msg.get(0).indexOf("-"));
		content = new Content(confname,members);
		
		System.out.println(content.getConfname());
		for(String a:content.getConfmember()){
			System.out.println(a);
		}
		
		return content;
	}
}
