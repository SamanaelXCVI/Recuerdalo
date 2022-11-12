package app.recuerdalo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Sensores extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor rotationVectorSensor;
    private SensorEventListener rvListener;
    private TextView mensajes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores);
        mensajes = findViewById(R.id.tvMensajes);
        sensorManager =
                (SensorManager) getSystemService(SENSOR_SERVICE);
        rotationVectorSensor =

                sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        rvListener = new SensorEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float[] rotationMatrix = new float[16];

                SensorManager.getRotationMatrixFromVector(rotationMatrix,
                        sensorEvent.values);
                float[] remappedRotationMatrix = new float[16];
                SensorManager.remapCoordinateSystem(rotationMatrix,
                        SensorManager.AXIS_X,
                        SensorManager.AXIS_Z,
                        remappedRotationMatrix);
                float[] orientations = new float[3];
                SensorManager.getOrientation(remappedRotationMatrix,
                        orientations);
                for(int i = 0; i < 3; i++) {
                    orientations[i] =
                            (float)(Math.toDegrees(orientations[i]));
                }
                if(orientations[2] > 45) {

                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                    mensajes.setText("Que hoy sea mejor que ayer y que mañana sea mejor");
                } else if(orientations[2] < -45) {

                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                    mensajes.setText("Nunca te desanimes");
                } else if(Math.abs(orientations[2]) < 10) {

                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                    mensajes.setText("Ten un buen dia");
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };
    }
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(rvListener,
                rotationVectorSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(rvListener);
    }
}
