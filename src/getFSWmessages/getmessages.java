package getFSWmessages;

import Json.JsonOperation;

public class getmessages {
	public static void main(String[] args) {
		//LinuxCMD cmd = new LinuxCMD();
		//MessageFilter filter=new MessageFilter();
		//			cmd.runCommand(cmd.cmd_channels);
		//filter.GetConfs();
//		DbOperation dbo= new DbOperation();
//		dbo.getdbcontent();
		JsonOperation op = new JsonOperation();
		op.tojson("all",null,null,null);
	}
}
