package com.gerime.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.gerime.R;
import com.gerime.model.AccountMoviment;
import com.gerime.model.User;
import com.gerime.repository.EntityManager;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		((Button) findViewById(R.id.in))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						User user = new User();
						user.setName(LoginActivity.this.getText(R.id.user_name)
								.toString());
						user.setPassword(LoginActivity.this.getText(
								R.id.password).toString());
						EntityManager.getSqlAdapter().store(user);
						Intent intent = new Intent(LoginActivity.this,
								DashboradActivity.class);
						createDefaltMovements();
						LoginActivity.this.startActivity(intent);
						LoginActivity.this.finish();
					}
				});
	}

	protected void createDefaltMovements() {
		AccountMoviment am = new AccountMoviment();
		am.setDescription("Luz");
		am.setDate("15");
		am.setType(AccountMoviment.OUT);
		am.setValue(60.00f);
		EntityManager.getSqlAdapter().store(am);

		am = new AccountMoviment();
		am.setDescription("Agua");
		am.setDate("20");
		am.setValue(30.10f);
		am.setType(AccountMoviment.OUT);
		EntityManager.getSqlAdapter().store(am);

		am = new AccountMoviment();
		am.setDescription("Telefone");
		am.setDate("17");
		am.setValue(75.55f);
		am.setType(AccountMoviment.OUT);
		EntityManager.getSqlAdapter().store(am);

		am = new AccountMoviment();
		am.setDescription("Alimentacao");
		am.setDate("1");
		am.setValue(400.30f);
		am.setType(AccountMoviment.OUT);
		EntityManager.getSqlAdapter().store(am);

		am = new AccountMoviment();
		am.setDescription("Financiamente / Aluguel");
		am.setDate("30");
		am.setValue(650.30f);
		am.setType(AccountMoviment.OUT);
		EntityManager.getSqlAdapter().store(am);

		am = new AccountMoviment();
		am.setDescription("Farmacia");
		am.setDate("30");
		am.setValue(80.30f);
		am.setType(AccountMoviment.OUT);
		EntityManager.getSqlAdapter().store(am);

		am = new AccountMoviment();
		am.setDescription("Farmacia");
		am.setDate("30");
		am.setValue(80.30f);
		am.setType(AccountMoviment.OUT);
		EntityManager.getSqlAdapter().store(am);

		am = new AccountMoviment();
		am.setDescription("Financiamente / Aluguel");
		am.setDate("30");
		am.setValue(650.30f);
		am.setType(AccountMoviment.OUT);
		EntityManager.getSqlAdapter().store(am);

		am = new AccountMoviment();
		am.setDescription("Salario");
		am.setDate("30");
		am.setValue(1470.80f);
		am.setType(AccountMoviment.IN);
		EntityManager.getSqlAdapter().store(am);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
