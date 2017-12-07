package watt.w170803.util.pedidos;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Pablo Henrique Correa on 06/12/2017.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Tab1", "Tab2", "Tab3" };
    private Context context;
    private String clienteSelecionado;

    public PagerAdapter(FragmentManager fm, Context context, String cliente) {
        super(fm);
        this.context = context;
        clienteSelecionado = cliente;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            FragTela1NovoPedido frag1 = FragTela1NovoPedido.newInstance(clienteSelecionado);
            return frag1;
        } else if (position == 1) {
            return new FragTela2ProdutosNovoPedido();
        }
        return new FragTela3ResumoNovoPedido();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
