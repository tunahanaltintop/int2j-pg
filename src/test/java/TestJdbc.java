import tun.int2jpg.dao.Int2jpgDao;

public class TestJdbc {
    public static void main(String[] args) {
        Int2jpgDao.runSqlAndPrintColumn("sql","column");
    }
}
