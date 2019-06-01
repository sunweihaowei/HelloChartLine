package com.itheima.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.view.LineChartView;
/*
HelloChrat的线型图LineChartView总结：
1.建立布局，初始化控件，
在建立setLineChartData( data );//把线型图数据建立到布局上
数据：LineChartData：XAxis轴，Y轴，折线Line，加进去
设置值时：先创建int或String值，然后在将值加到list里（这里要类型，要new）
//注意：线值new时没有提示，需要自己记住new LineValue（建在哪里，值）


*/
public class Main2Activity extends AppCompatActivity {

    private LineChartView iv_chart;
    private int[] temperature={1,3,2,4,5,3,7};
    //加[]就可以这样写，如下：
    private String[] lineData=new String[]{"周1","周2","周3","周4","周5","周6","周7"};
    private List<PointValue> pointValues=new ArrayList <>(  );
    private List<AxisValue> axisValues=new ArrayList <>(  );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main2 );
        initView();
        setAxisXLables();
        setAxisPoints();
        initLineChart();
    }
    private void setAxisXLables(){
        for (int i=0;i<lineData.length;i++){
            //new AxisValue( i ).setLabel( lineData[i] ),即加入到值里的标签
            axisValues.add( new AxisValue( i ).setLabel( lineData[i] ) );
        }
    }
    private void setAxisPoints(){
        for (int i=0;i<temperature.length;i++){
            pointValues.add( new PointValue(i,temperature[i] ) );//(加入到几项，要加入的值值)
        }
    }
    private void initLineChart(){
        Line line=new Line(  );//创建线
        line.setColor( Color.parseColor( "#33b5e5" ) );//设置线的颜色（这里用颜色解析来解析颜色数据，解析为系统认识的数据）
        line.setShape( ValueShape.DIAMOND );//设置线节点的形状ValueShape.CIRCLE
        line.setCubic( true );//(设置曲线还是折线)设置是否平滑
        line.setHasLabels( true );//设置备注
        line.setHasLines( true );//设置线是否可见
        line.setValues( pointValues );//设置点上的值
        List<Line>  lines=new ArrayList <>(  );//创建ArrayList，
        lines.add( line );//把线加到项list上去
        LineChartData data=new LineChartData(  );//实例化图表数据
        data.setLines( lines );//把线加到图表数据上去
        //X轴
        Axis axisX=new Axis(  );
        axisX.setHasTiltedLabels( true );//true为全部显示，false为显示单活双
        axisX.setTextColor( Color.GREEN );//设置线和字体的颜色
        axisX.setMaxLabelChars( 5 );//设置轴与值的宽度
        axisX.setValues( axisValues );// 填充X轴的坐标名称
        data.setAxisXBottom( axisX );//建立位置（即为我们说的X轴）
        axisX.setHasLines( false );//设置X轴分割线(即为竖线，那几条)
        //Y轴
        Axis axisY=new Axis(  );
        data.setAxisYLeft( axisY );
        axisY.setTextColor( Color.BLACK );
        axisY.setMaxLabelChars( 5 );
        //设置线形图的行为属性，如支持缩放，滑动以及平移
        iv_chart.setInteractive( true );
        iv_chart.setZoomType( ZoomType.HORIZONTAL );//设置缩放类型，为水平
        iv_chart.setMaxZoom( (float) 2 );//最大缩放倍数
        //设置是否可滑动
        iv_chart.setContainerScrollEnabled( true, ContainerScrollType.HORIZONTAL );
        iv_chart.setLineChartData( data );//把线型图建立到布局上
        iv_chart.setVisibility( View.VISIBLE );//设置是否可见，这里为可见
    }
    private void initView() {
        iv_chart = (LineChartView) findViewById( R.id.iv_chart );
    }
}
