package com.loggar.service.mail;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext-test.xml")
public class MailSenderSampleTest {
	@Autowired ApplicationContext applicationContext;
	
	public class MailServiceTemp {
		protected MailSenderTransaction mailSenderTransaction;

		public void setMailSenderTransaction(MailSenderTransaction mailSenderTransaction) {
			this.mailSenderTransaction = mailSenderTransaction;
		}
		
		public MailSenderTransaction getMailSenderTransaction() {
			return this.mailSenderTransaction;
		}
		
		protected int sendMailForLevelUpgradeInform(String[] users) {
			List<String> userList = new ArrayList<String>(Arrays.asList(users));
			return sendMailForLevelUpgradeInform(userList);
		}

		protected int sendMailForLevelUpgradeInform(List<String> users) {
			String from = "abc@webnlog.com";
			String subject = "Level Upgrade Infom";

			boolean isCommit = true;

			for (String user : users) {
				if (user == null || user.isEmpty()) {
					isCommit = false;
					break;
				} else {
					SimpleMailMessage msg = new SimpleMailMessage();
					msg.setTo(user);
					msg.setFrom(from);
					msg.setSubject(subject);
					msg.setText("Upgrade 되었습니다.");
					mailSenderTransaction.addMsg(msg);
				}
			}

			if (isCommit) {
				int sendResult = mailSenderTransaction.sendAll();
				mailSenderTransaction.clearMsg();
				return sendResult;
			} else {
				mailSenderTransaction.clearMsg();
				return 0;
			}
		}
	}
	
	@Test
	public void sendmailTest() {
		String[] users = {"abc1@abc.com", "abc12@abc.com", "abc3@abc.com"," abc4@abc.com", "abc5@abc.com"};
		
		MailServiceTemp test = new MailServiceTemp();
		test.setMailSenderTransaction((MailSenderTransaction)applicationContext.getBean("mailSenderTransaction"));
		
		assertThat(test.sendMailForLevelUpgradeInform(users), is(5));
		assertThat(test.getMailSenderTransaction().isEmpty(), is(true));
		
		String[] users2 = {"abc1@abc.com", "abc12@abc.com", "abc3@abc.com","", "abc5@abc.com"};
		assertThat(test.sendMailForLevelUpgradeInform(users2), is(0));
		assertThat(test.getMailSenderTransaction().isEmpty(), is(true));
		
	}
}
