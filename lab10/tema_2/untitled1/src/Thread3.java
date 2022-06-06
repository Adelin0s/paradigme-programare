public class Thread3 implements Runnable
{
    private int m_vec[];

    Thread3(final int [] vec)
    {
        m_vec = vec;
    }

    public void print()
    {
        for (int i = 0; i < m_vec.length; i++)
            System.out.println(m_vec[i] + " ");
    }

    @Override
    public void run()
    {
        print();
    }
}
