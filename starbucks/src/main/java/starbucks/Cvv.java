package starbucks;

public class Cvv implements IDisplayComponent, ITouchEventHandler, IKeyPadObserver {
	 static StringBuilder cvv;
	ITouchEventHandler nextHandler;
	IApp app;

	Cvv() {
		cvv = new StringBuilder("");
		app = (IApp) Device.getInstance();
	}

	public StringBuilder getCvv() {
		return cvv;
	}

	@Override
	public void keyEventUpdate(int numKeys, String key) {
		if (app.isFocusCvv()) {
			cvv = (convertKeyEvent(key));
		}

	}

	@Override
	public void touch(int x, int y) {
		String s = x + "," + y;
		switch (s) {
		case "1,2":
		case "2,2":
		case "3,2":
			app.setFocusCvv(false);
			break;
		default:
			break;

		}
		if (nextHandler != null) {
			nextHandler.touch(x, y);
		}

	}

	@Override
	public void setNext(ITouchEventHandler next) {
		nextHandler = next;

	}

	@Override
	public String display() {
		System.err.println("Cvv => display");
		return "[" + getCvv() + "]";
	}

	@Override
	public void addSubComponent(IDisplayComponent c) {
		// TODO Auto-generated method stub

	}

	/**
	 * Sets the Cvv for the cardx
	 * 
	 * @param x X Coord
	 * @param y Y Coord
	 */
	private StringBuilder convertKeyEvent(String key) {
		StringBuilder cvv = new StringBuilder(getCvv());
		switch (key) {
		case "X":
			if (cvv.length() == 0) {
				break;
			}
			cvv.deleteCharAt(cvv.length() - 1);
			break;
		case " ":
			break;
		default:
			if (cvv.length() == 3) {
				break;
			}
			cvv.append(key);
		}
		return cvv;
	}

}
