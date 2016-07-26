import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Layout;
import org.apache.log4j.helpers.Transform;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

public class MyLayout extends Layout {

	protected int BUF_SIZE = 256;
	protected int MAX_CAPACITY = 1024;
	private StringBuffer buffer;
	final String title = "My layout Log";

	public MyLayout() {
		this.BUF_SIZE = 256;
		this.MAX_CAPACITY = 1024;

		this.buffer = new StringBuffer(256);
	}

	public String getContentType() {
		return "text/html";
	}

	//Does not do anything as options become effective
	// OptionHandler : A string based interface to configure package components.
	public void activateOptions() {
	}

	public String format(LoggingEvent event) {
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		
		if (this.buffer.capacity() > 1024)
			this.buffer = new StringBuffer(256);
		else {
			this.buffer.setLength(0);
		}

		this.buffer.append(Layout.LINE_SEP + "<body>" + Layout.LINE_SEP);
		this.buffer.append(Layout.LINE_SEP + "<tr>" + Layout.LINE_SEP);

		//Coluna horário
		this.buffer.append("<td>");
		this.buffer.append(dateFormat.format(Calendar.getInstance().getTime()));
		this.buffer.append("</td>" + Layout.LINE_SEP);
		
        //Mensagem
		this.buffer.append("<td>");
		this.buffer.append(Transform.escapeTags(event.getRenderedMessage()));
		this.buffer.append("</td>" + Layout.LINE_SEP);
		

		//Pasta e linha
		LocationInfo locInfo = event.getLocationInformation();
		this.buffer.append("<td>");
		this.buffer.append(Transform.escapeTags(locInfo.getFileName()));
		this.buffer.append(':');
		this.buffer.append(locInfo.getLineNumber());
		
		this.buffer.append("</tr>" + Layout.LINE_SEP);

		return this.buffer.toString();
	}

	public String getHeader() {
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("<!DOCTYPE html>"
				+ Layout.LINE_SEP);
		sbuf.append("<html>" + Layout.LINE_SEP);
		sbuf.append("<head>" + Layout.LINE_SEP);
		sbuf.append("<title>" + this.title + "</title>" + Layout.LINE_SEP);
		sbuf.append("<style type=\"text/css\" scoped>" + Layout.LINE_SEP);
		sbuf.append("table.GeneratedTable {" + Layout.LINE_SEP);
		sbuf.append("width:100%;" + Layout.LINE_SEP);
		sbuf.append("background-color:#FFFFFF;" + Layout.LINE_SEP);
		sbuf.append("border-collapse:collapse;border-width:1px;" + Layout.LINE_SEP);
		sbuf.append("border-color:#000000;" + Layout.LINE_SEP);
		sbuf.append("border-style:solid;" + Layout.LINE_SEP);
		sbuf.append("color:#000000;" + Layout.LINE_SEP);
		sbuf.append("}" + Layout.LINE_SEP);
		sbuf.append("table.GeneratedTable td, table.GeneratedTable th {" + Layout.LINE_SEP);
		sbuf.append("border-width:1px;" + Layout.LINE_SEP);
		sbuf.append("border-color:#000000;" + Layout.LINE_SEP);
		sbuf.append("border-style:solid;" + Layout.LINE_SEP);
		sbuf.append("padding:3px;" + Layout.LINE_SEP);
		sbuf.append("}" + Layout.LINE_SEP);
		sbuf.append("table.GeneratedTable thead {" + Layout.LINE_SEP);
		sbuf.append("background-color:#FF99FF;" + Layout.LINE_SEP);
		sbuf.append("}" + Layout.LINE_SEP);
		sbuf.append("</style>" + Layout.LINE_SEP);
		sbuf.append("</head>" + Layout.LINE_SEP);
		sbuf.append("<table class=\"GeneratedTable\">"+ Layout.LINE_SEP);
		sbuf.append("<thead>" + Layout.LINE_SEP);
		sbuf.append("<tr>" + Layout.LINE_SEP);
		sbuf.append("<th width=\"20%\">Horário</th>" + Layout.LINE_SEP);
		sbuf.append("<th width=\"30%\">Mensagem</th>" + Layout.LINE_SEP);
		sbuf.append("<th width=\"50%\">Linha</th>" + Layout.LINE_SEP);
		sbuf.append("</tr>" + Layout.LINE_SEP);
		sbuf.append("</thead>" + Layout.LINE_SEP);
		sbuf.append("" + Layout.LINE_SEP);
		return sbuf.toString();
	}

	public String getFooter() {
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("</table>" + Layout.LINE_SEP);
		sbuf.append("<br>" + Layout.LINE_SEP);
		sbuf.append("</body></html>");
		return sbuf.toString();
	}

	/*If the layout handles the throwable object contained within LoggingEvent, 
	then the layout should return false. 
	Otherwise, if the layout ignores throwable object, then the layout should return true. 
	If ignoresThrowable is true, the appender is responsible for rendering the throwable.*/
	public boolean ignoresThrowable() {
		return true;
	}
}
