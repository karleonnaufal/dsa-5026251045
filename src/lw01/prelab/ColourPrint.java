package lw01.prelab;
public class ColourPrint extends PrintJob {
    private static final int FIRST_TIER_PAGES = 10;
    private static final int FIRST_TIER_RATE = 1500;
    private static final int NEXT_TIER_RATE = 1000;
    private static final int SETUP_FEE = 2000;

    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        int firstTier = Math.min(getPages(), FIRST_TIER_PAGES);
        int beyondTier = Math.max(getPages() - FIRST_TIER_PAGES, 0);
        return firstTier * FIRST_TIER_RATE + beyondTier * NEXT_TIER_RATE + SETUP_FEE;
    }

    @Override
    public String label() { return "Colour"; }
}