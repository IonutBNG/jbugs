package bug.dto;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
public class BugStatisticsDto {
    private Integer totalBugs;
    private Integer totalOpen;
    private Integer totalRejected;
    private Integer totalInProgress;
    private Integer totalInfoNeeded;
    private Integer totalFixed;
    private Integer totalClosed;

    public BugStatisticsDto() {
    }

    public Integer getTotalBugs() {
        return totalBugs;
    }

    public void setTotalBugs(Integer totalBugs) {
        this.totalBugs = totalBugs;
    }

    public Integer getTotalOpen() {
        return totalOpen;
    }

    public void setTotalOpen(Integer totalOpen) {
        this.totalOpen = totalOpen;
    }

    public Integer getTotalRejected() {
        return totalRejected;
    }

    public void setTotalRejected(Integer totalRejected) {
        this.totalRejected = totalRejected;
    }

    public Integer getTotalInProgress() {
        return totalInProgress;
    }

    public void setTotalInProgress(Integer totalInProgress) {
        this.totalInProgress = totalInProgress;
    }

    public Integer getTotalInfoNeeded() {
        return totalInfoNeeded;
    }

    public void setTotalInfoNeeded(Integer totalInfoNeeded) {
        this.totalInfoNeeded = totalInfoNeeded;
    }

    public Integer getTotalFixed() {
        return totalFixed;
    }

    public void setTotalFixed(Integer totalFixed) {
        this.totalFixed = totalFixed;
    }

    public Integer getTotalClosed() {
        return totalClosed;
    }

    public void setTotalClosed(Integer totalClosed) {
        this.totalClosed = totalClosed;
    }
}

