package toi.assignment.simulator.util;

public class Node extends Thread {

	protected long packageSize;

	protected long waitingPeriod;

	protected int totalPackages;

	protected int failureCount;

	protected int totalCount;

	protected int successCount;

	protected long timeSpent;

	protected String name;

	private Thread t;

	protected int packageRemaining;

	public String GetName() {
		return this.name;
	}

	protected Boolean busy;

	public Boolean IsBusy() {
		return busy;
	}

	public long getPackageSize() {
		return packageSize;
	}

	public void setPackageSize(long packageSize) {
		this.packageSize = packageSize;
	}

	public long getWaitingPeriod() {
		return waitingPeriod;
	}

	public void setWaitingPeriod(long waitingPeriod) {
		this.waitingPeriod = waitingPeriod;
	}

	public int getTotalPackages() {
		return totalPackages;
	}

	public void setTotalPackages(int totalPackages) {
		this.totalPackages = totalPackages;
	}

	public int getFailureCount() {
		return this.totalCount - this.successCount;
	}

	public void setFailureCount(int failureCount) {
		this.failureCount = failureCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}

	public Boolean getBusy() {
		return busy;
	}

	public void setBusy(Boolean busy) {
		this.busy = busy;
	}

	public Node getReciever() {
		return reciever;
	}

	public void setReciever(Node reciever) {
		this.reciever = reciever;
	}

	protected Node reciever;

	/**
	 * constructor
	 * 
	 * @param packageSize
	 * @param waitingPeriod
	 * @param totalPackages
	 */
	public Node(long packageSize, long waitingPeriod, int totalPackages, String name) {
		this.packageSize = packageSize;
		this.waitingPeriod = waitingPeriod;
		this.totalPackages = totalPackages;
		this.name = name;
		this.busy = false;
		this.failureCount = 0;
		this.successCount = 0;
		this.totalCount = 0;
		this.packageRemaining = this.totalPackages;
		this.timeSpent = 0;
	}

	public Node(String name) {
		this.packageSize = 0;
		this.waitingPeriod = 0;
		this.totalPackages = 0;
		this.name = name;
		this.busy = false;
		this.failureCount = 0;
		this.successCount = 0;
		this.totalCount = 0;
		this.packageRemaining = this.totalPackages;
		this.timeSpent = 0;
	}

	public boolean send() {
		return false;
	};

	public boolean recieve(Node node, Long packageSize) {
		if (this.busy == false) {
			this.busy = true;
			try {
				Thread.sleep(packageSize);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
				return false;
			}
			this.busy = false;
			return true;
		}
		return false;
	}

	/**
	 * run thread
	 */
	public void run() {
		while (this.packageRemaining > 0) {
			if (this.send()) {
				this.packageRemaining--;
				this.successCount++;
			}
		}
		Monitor.PrintSummary(this);
	}

	public String toString() {
		return name + " finished transmiting with: TotalCount[" + this.totalCount + "] | Success [" + this.successCount
				+ "] | TotalTime [" + this.getTimeSpent() + "] | PackageSent [" + this.getTotalPackageSent()
				+ "] | Utilization [" + this.getUtilization() + "] | Throughput [" + this.getThroughput() + "]";
	}

	public double getUtilization() {
		double result = ((double) this.successCount / (double) this.totalCount);
		return result;
	}

	public long getTotalPackageSent() {
		return this.packageSize * this.totalPackages;
	}

	public double getThroughput() {
		return (double) this.getTotalPackageSent() / (double) this.getTimeSpent();
	}

	public long getTimeSpent() {
		return this.timeSpent;
	}

	public void start() {
		//System.out.println("Starting " + this.name);
		if (t == null) {
			t = new Thread(this, this.name);
			t.start();
		}
	}

	public int compareTo(Node other) {
		return name.compareTo(other.name);
	}
	
	
	protected void Rest (boolean result) {
		try {
			if (result) {
				long sleep = this.waitingPeriod  >  this.packageSize  ? ( this.waitingPeriod - this.packageSize)  : 0;
				Thread.sleep(sleep);
			} else {
				Thread.sleep(this.waitingPeriod);
			}	
			this.timeSpent += this.waitingPeriod > this.packageSize ? this.waitingPeriod : this.packageSize;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
