package sur.softsurena.metodos;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class ListenerConfig implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        LOG.info("Prueba iniciada: ".concat(result.getName()));
        LOG.info("Tiempo de duracion: ".concat(
                String.valueOf(
                        result.getEndMillis() - result.getStartMillis()
                )
        ));
        
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOG.info("Prueba satifctoria: ".concat(result.getName()));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOG.info("Prueba fallida: ".concat(result.getName()));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LOG.info("Prueba Ignorada: ".concat(result.getName()));
        LOG.info("Causa: ".concat(result.getSkipCausedBy().toString()));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        LOG.info("Inicio de ejecucion de la prueba.");
        LOG.info("Nombre de la prueba: ".concat(context.getName()));
        ITestNGMethod[] metodos = context.getAllTestMethods();
        LOG.info("Metodos para ejecutar.");
        for (ITestNGMethod metodo : metodos) {
            LOG.info(metodo.getMethodName());
        }
        
    }

    @Override
    public void onFinish(ITestContext context) {
//        LOG.info("Ubicacion del xml: ".concat(context.getCurrentXmlTest().getSuite().getFileName()));
        LOG.info("Prueba terminada -> ".concat(context.getName()));
    }

}
