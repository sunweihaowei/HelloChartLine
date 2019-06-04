package com.itheima.myapplication.HelloChart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.itheima.myapplication.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class PieChartViewActivity extends AppCompatActivity {
    private PieChartData data;
    private PieChartView pcv;
    private int[] pieData=new int[]{
    10,20,30,35,5
    };
    private int[] color=new int[]{
            Color.parseColor( "#356fb3" ),
            Color.parseColor( "#b53633" ),
            Color.parseColor( "#86aa3d" ),
            Color.parseColor( "#6a4b90" ),
            Color.parseColor( "#2e9cba" )
    };
    List<SliceValue> sliceValues=new ArrayList <>(  );
    private String[] stateChar={"高等教育1","高等教育2","高等教育3","高等教育4","高等教育5"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_pie_chart_view );
        initView();

        slice();
        initPie();
        final PieChartOnValueSelectListener pieChartOnValueSelectListener=new PieChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int i, SliceValue sliceValue) {//i表示点击的那一项，slicevalue为点击的那个的值
                data.setCenterText1( stateChar[i] );
                data.setCenterText2( sliceValue.getValue()+"("+calPercent( i )+")" );
            }

            @Override
            public void onValueDeselected() {

            }
        };
        pcv.setOnValueTouchListener( pieChartOnValueSelectListener );
    }

    private void slice() {
        for (int i=0;i<pieData.length;i++){
        SliceValue sliceValue=new SliceValue( pieData[i],color[i] );
        sliceValues.add( sliceValue );
        }
    }

    private void initPie() {
        data=new PieChartData(  );
        data.setCenterCircleColor( Color.WHITE );//设置中心圆的颜色
        data.setHasCenterCircle( true );//设置是否有中心圆

        data.setHasLabels( true );//是否有数据显示
        data.setCenterCircleScale( 0.5f );//设置中心相对于全部的占比大小
        data.setCenterText1( "数据" );//设置center默认数据
        data.setHasLabelsOnlyForSelected( true );//这个表示是否选中时才显示数据，true为选中时才有数据
        data.setHasLabelsOutside( false );//这个表示数据是否显示在外面
        data.setValues(sliceValues);//这个表示为PieChartData建立数据（List类型数据）

        pcv.setPieChartData( data );
        pcv.setValueSelectionEnabled( true );//是否可以选择，true为可以且会变大
        pcv.setAlpha( 0.9f );//透明度
        pcv.setCircleFillRatio( 1 );//设置相对于view大小，默认为全部
    }
    private String calPercent(int i){//有参数就要return,
        String result="";
        int sum=0;
        for (int j=0;j<pieData.length;j++){//这里计算出pieData的全部值的和，sum
            sum+=pieData[j];
        }
        result=String.format( "%.2f",(float)pieData[i]*100/sum )+"%";
        return result;
    }
    private void initView() {
        pcv = (PieChartView) findViewById( R.id.pcv );
    }
}
