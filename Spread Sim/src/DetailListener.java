import java.util.EventListener;

public interface DetailListener extends EventListener{
	public void detailEventOccurred(DetailEvent event);
	public void detailEventOccurred(DetailEventDayChange event);
}
