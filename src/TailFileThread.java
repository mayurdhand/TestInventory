import java.io.File;
import java.io.RandomAccessFile;


public class TailFileThread implements Runnable{
	
	public boolean _running = true;
	public File _file = new File("D:\\market_data.log");
	public long _updateInterval = 3000;
	long _filePointer = 0;
	
	public void run() {
	    try {
	        while (_running) {
	            Thread.sleep(_updateInterval);
	            long len = _file.length();
	            if (len < _filePointer) {
	                // Log must have been jibbled or deleted.
	                System.out.println("Log file was reset. Restarting logging from start of file.");
	                _filePointer = len;
	            }
	            else if (len > _filePointer) {
	                // File must have had something added to it!
	                RandomAccessFile raf = new RandomAccessFile(_file, "r");
	                raf.seek(_filePointer);
	                String line = null;
	                while ((line = raf.readLine()) != null) {
	                    System.out.println(line);
	                }
	                _filePointer = raf.getFilePointer();
	                raf.close();
	            }
	        }
	    }
	    catch (Exception e) {
	    	System.out.println("Fatal error reading log file, log tailing has stopped.");
	    }
	}

	public static void main(String[] args) {
		new TailFileThread().run();
	}
}
