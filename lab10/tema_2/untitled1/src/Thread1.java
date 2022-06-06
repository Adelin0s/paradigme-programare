public class Thread1 implements Runnable
{
    private int m_alpha;
    private int [] m_vec;

    public Thread1(int[] vec, final int alpha) {
        m_vec = vec;
        m_alpha = alpha;
    }

    private void make_product() {
        for (int i = 0; i < m_vec.length; i++)
            m_vec[i] *= m_alpha;
    }

    @Override
    public void run()
    {
        make_product();
    }
}
