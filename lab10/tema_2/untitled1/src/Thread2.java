public class Thread2 implements Runnable
{
    private int[] m_vec;

    public Thread2(final int[] vec)
    {
        m_vec = vec;
    }

    public void sort() {
        for (int i =0 ; i < m_vec.length; i++)
            for (int j = i + 1; j < m_vec.length; j++)
                if (m_vec[i] > m_vec[j])
                {
                    int temp = m_vec[i];
                    m_vec[i] = m_vec[j];
                    m_vec[j] = temp;
                }
    }

    @Override
    public void run()
    {
        sort();
    }
}
