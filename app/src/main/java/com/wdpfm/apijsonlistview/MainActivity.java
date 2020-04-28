package com.wdpfm.apijsonlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.song.http.QSHttp;
import org.song.http.framework.QSHttpCallback;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<noticeList> noticeList = new ArrayList<noticeList>();
    private MyAdapter myAdapter;
    final String TAG="测试";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        String url = "https://hb5.api.okayapi.com/?&s=App.SuperTable.FreeQuery&model_name=charge&database=super&logic=and&where=[[\"1\",\"=\",\"1\"]]"+Key.key;
        QSHttp.postJSON(url)
                .jsonModel(Root.class)
                .buildAndExecute(new QSHttpCallback<Root>() {
                    @Override
                    public void onComplete(Root data) {
                        noticeList =data.getData().getList();
                        //实例化适配器
                        myAdapter = new MyAdapter();
                        //设置适配器
                        listView.setAdapter(myAdapter);
                    }
                });
    }
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return noticeList.size();
        }

        @Override
        public Object getItem(int i) {
            return noticeList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LinearLayout layout = new LinearLayout(MainActivity.this);
            layout.setOrientation(LinearLayout.HORIZONTAL);

            TextView textViewPid = new TextView(MainActivity.this);
            textViewPid.setText(noticeList.get(i).getDate() + "\t\t\t\t");

            TextView textViewTitle = new TextView(MainActivity.this);
            textViewTitle.setText(noticeList.get(i).getTitle() + "\t\t\t\t" + " ");

            TextView textViewContent = new TextView(MainActivity.this);
            textViewContent.setText(noticeList.get(i).getContent()+ "\t\t\t\t");

            layout.addView(textViewPid);
            layout.addView(textViewTitle);
            layout.addView(textViewContent);

            return layout;
        }
    }
}
class noticeList
{
    private int id;

    private String title;

    private String content;

    private String date;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }
    public void setDate(String date){
        this.date = date;
    }
    public String getDate(){
        return this.date;
    }
}

class Data
{
    private int err_code;

    private String err_msg;

    private int total;

    private List<noticeList> list;

    private int page;

    private int perpage;

    public void setErr_code(int err_code){
        this.err_code = err_code;
    }
    public int getErr_code(){
        return this.err_code;
    }
    public void setErr_msg(String err_msg){
        this.err_msg = err_msg;
    }
    public String getErr_msg(){
        return this.err_msg;
    }
    public void setTotal(int total){
        this.total = total;
    }
    public int getTotal(){
        return this.total;
    }
    public void setList(List<noticeList> list){
        this.list = list;
    }
    public List<noticeList> getList(){
        return this.list;
    }
    public void setPage(int page){
        this.page = page;
    }
    public int getPage(){
        return this.page;
    }
    public void setPerpage(int perpage){
        this.perpage = perpage;
    }
    public int getPerpage(){
        return this.perpage;
    }
}

class Root
{
    private int ret;

    private Data data;

    private String msg;

    public void setRet(int ret){
        this.ret = ret;
    }
    public int getRet(){
        return this.ret;
    }
    public void setData(Data data){
        this.data = data;
    }
    public Data getData(){
        return this.data;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }
}
