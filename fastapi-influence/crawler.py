from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.common.keys import Keys
import time

# options = Options()
# options.add_experimental_option('detach', True) # 브라우저 바로 닫힘 방지

# service = Service(ChromeDriverManager().install())

# print(service)

# driver = webdriver.Chrome(service=service, options=options)

# driver.get('https://naver.com')

def crawl_naver():
    options = Options()
    options.add_experimental_option('detach', True)
    
    service = Service(ChromeDriverManager().install())
    
    driver = webdriver.Chrome(service=service, options=options)
    
    # driver.get('https://naver.com')
    driver.get('https://www.instagram.com/accounts/login/')
    
    
    # 크롤링 로직을 여기에 추가
    
    driver.implicitly_wait(3)
    id = driver.find_element(By.NAME, 'username')
    id.send_keys('daegu_on')
    time.sleep(2)
    
    password = driver.find_element(By.NAME, 'password')
    password.send_keys('tlstjd0045')
    time.sleep(2)
    
    login_button = driver.find_element(By.CSS_SELECTOR, 'button[type=submit]').click()
    time.sleep(5)
    
    # 로그인 정보 저장 여부 ("나중에 하기 버튼 클릭")
    btn_later1 = driver.find_element(By.CLASS_NAME,'_ac8f')
    btn_later1.click()
    time.sleep(5)
    
    # 알림 설정 ("나중에 하기 버튼 클릭")
    btn_later2 = driver.find_element(By.CLASS_NAME,'_a9-z')
    btn_later2.click()
    time.sleep(5)



    
    title = "API TEST"
    
    return title
