package com.google.research.reflection.predictor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class a {
    protected HashMap<Integer, Long> Mq;
    protected b Mr;
    protected HashMap<String, Integer> Ms;
    private float[] Mt;
    protected int Mu;
    protected int Mv;
    protected HashMap<Integer, Integer> Mw;
    private c Mx;
    private d My;
    protected HashMap<Integer, Long> Mz;

    public a() {
        this.Mw = new HashMap<>();
        this.Ms = new HashMap<>();
        this.Mq = new HashMap<>();
        this.Mz = new HashMap<>();
        this.Mt = null;
        this.Mu = 100;
    }

    public a(final b mr) {
        this.Mw = new HashMap<>();
        this.Ms = new HashMap<>();
        this.Mq = new HashMap();
        this.Mz = new HashMap<>();
        this.Mt = null;
        this.Mu = 100;
        (this.Mr = mr).Sr(this);
    }

    private String Se() {
        final long n = Long.MAX_VALUE;
        final Iterator<Map.Entry<Integer, Long>> iterator = this.Mz.entrySet().iterator();
        Integer n2 = null;
        long longValue = n;
        while (iterator.hasNext()) {
            final Map.Entry<Integer, Long> entry = iterator.next();
            int n3;
            if (entry.getValue() >= longValue) {
                n3 = 1;
            } else {
                n3 = 0;
            }
            Integer n5;
            if (n3 == 0) {
                final Integer n4 = entry.getKey();
                longValue = entry.getValue();
                n5 = n4;
            } else {
                n5 = n2;
            }
            n2 = n5;
        }
        for (final Map.Entry<String, Integer> entry2 : this.Ms.entrySet()) {
            if (entry2.getValue().equals(n2)) {
                return entry2.getKey();
            }
        }
        return null;
    }

    private void Sg(final String s, final String s2) {
        if (!this.Ms.isEmpty()) {
            final Integer n = this.Ms.remove(s);
            if (n != null) {
                this.Ms.put(s2, n);
            }
            this.Mr.So(s, s2);
        }
    }

    private void Sm(final String s) throws PredictorWrapper.PredictorInvalidException {
        if (this.Ms.isEmpty()) {
            return;
        }
        final int n = this.Ms.size() - 1;
        final Integer value = this.Ms.remove(s);
        if (!this.Ms.isEmpty()) {
            if (value == null) {
                this.Mr.Ss(null, null, s);
                if (this.Mx != null) {
                    this.Mx.Sy(value, n, s);
                }
            } else {
                this.Mv -= this.Mw.remove(value);
                this.Mq.remove(value);
                this.Mz.remove(value);
                if (n > value) {
                    for (final Map.Entry<String, Integer> entry : this.Ms.entrySet()) {
                        if (entry.getValue() == n) {
                            entry.setValue(value);
                            break;
                        }
                    }
                    this.Mw.put(value, this.Mw.remove(n));
                    this.Mq.put(value, this.Mq.remove(n));
                    this.Mz.put(value, this.Mz.remove(n));
                }
                this.Mr.Ss(value, n, s);
                if (this.Mx != null) {
                    this.Mx.Sy(value, n, s);
                }
            }
            return;
        }
        throw new PredictorWrapper.PredictorInvalidException("Predictor becomes invalid");
    }

    public d Sd() {
        return this.My;
    }

    public e Sf(final com.google.research.reflection.a.b b) {
        if (this.Mt == null || this.Ms.size() > this.Mt.length) {
            this.Mt = new float[this.Ms.size()];
        }
        if (this.Mt.length > 0) {
            Arrays.fill(this.Mt, 0.0f);
        }
        final e sv = this.Mr.Sv(this.Mt, b);
        final ArrayList<f> list = new ArrayList<>(this.Ms.size());
        final float[] si = sv.SI();
        for (final Map.Entry<String, Integer> entry : this.Ms.entrySet()) {
            list.add(new f(entry.getKey(), si[entry.getValue()]));
        }
        Collections.sort(list, Collections.reverseOrder());
        sv.SK(list);
        return sv;
    }

    public e Sh(final com.google.research.reflection.a.b b) {
        if (!this.Ms.containsKey(b.getId()) && this.Sn().size() == this.Sk()) {
            final String se = this.Se();
            if (se != null) {
                try {
                    this.Sm(se);
                } catch (PredictorWrapper.PredictorInvalidException ex) {
                }
            }
        }
        final e sq = this.Mr.Sq(b);
        final int si = this.Si(b);
        Integer value = this.Mw.get(si);
        if (value == null) {
            value = 0;
        }
        this.Mw.put(si, value + 1);
        ++this.Mv;
        this.Mz.put(si, b.F());
        return sq;
    }

    public int Si(final com.google.research.reflection.a.b b) {
        final String id = b.getId();
        final long longValue = b.F();
        Integer value = this.Ms.get(id);
        if (value == null) {
            value = this.Ms.size();
            this.Ms.put(id, value);
            this.Mq.put(value, longValue);
        }
        return value;
    }

    public void Sj(final d my) {
        this.My = my;
    }

    public int Sk() {
        return this.Mu;
    }

    public void Sl(final String s, final String s2, final Map map) {
        int n = 0;
        final ArrayList<String> list = new ArrayList<String>();
        for (final String s3 : this.Ms.keySet()) {
            if (s3.startsWith(s)) {
                list.add(s3);
            }
        }
        for (final String s4 : list) {
            while (this.Ms.containsKey(s2 + n)) {
                ++n;
            }
            final String string = s2 + n;
            map.put(s4, string);
            this.Sg(s4, string);
        }
    }

    public HashMap<String, Integer> Sn() {
        return this.Ms;
    }
}
