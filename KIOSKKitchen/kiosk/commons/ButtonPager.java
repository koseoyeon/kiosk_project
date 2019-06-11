package com.kiosk.commons;

import java.awt.Component;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class ButtonPager {

	// ��������� int cnt=0;���������ٰ�
	public int defaultRender(int showMax, int cnt, JComponent container, List list) {

		for (int i = 0; i < list.size(); i++) {
			if (i > (cnt * showMax) - 1 && i < ((cnt + 1) * showMax)) {
				// p.remove(list.get(i));
				container.add((Component) list.get(i));
			} else if (((cnt + 1) * showMax) - 1 < i && i < ((cnt + 2) * showMax)) {
				// p.add(list.get(i));
				container.remove((Component) list.get(i));

			} else if (cnt == 0) {
				for (int a = 0; a < showMax; a++) {
					container.add((Component) list.get(a));
				}
			}

		}
		container.revalidate();
		container.repaint();
		return cnt;

	}

	// ��������� int cnt=0;���������ٰ�
	public int beforeRender(int showMax, int cnt, JComponent container, List list) {
		cnt--;
		if (cnt < 0) {
			JOptionPane.showMessageDialog(container, "ó�� �������Դϴ�");
			cnt++;

			return cnt;
		}

		for (int i = 0; i < list.size(); i++) {
			if (i > (cnt * showMax) - 1 && i < ((cnt + 1) * showMax)) {
				// p.remove(list.get(i));
				container.add((Component) list.get(i));
			} else if (((cnt + 1) * showMax) - 1 < i && i < ((cnt + 2) * showMax)) {
				// p.add(list.get(i));
				container.remove((Component) list.get(i));
			} else if (cnt == 0) {
				for (int a = 0; a < showMax; a++) {
					container.add((Component) list.get(a));
				}
			}

		}
		container.revalidate();
		container.repaint();
		return cnt;
	}

	// ��������� int cnt=0;���������ٰ�
	public int afterRender(int showMax, int cnt, JComponent container, List list) {
		cnt++;
		if ((cnt >= list.size() / showMax && list.size() % showMax == 0) || cnt > list.size() / showMax) {
			JOptionPane.showMessageDialog(container, "������ ������ �Դϴ�");
			cnt--;
			return cnt;
		}
		for (int i = 0; i < list.size(); i++) {
			if (i < (cnt * showMax)) {
				container.remove((Component) list.get(i));
			} else if (i >= cnt * showMax && i < (cnt + 1) * showMax) {
				container.add((Component) list.get(i));
			}
		}
		container.revalidate();
		container.repaint();
		return cnt;
	}

}
