from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.common.keys import Keys
import time


def crawl_instagram(instagram_id):
    options = Options()
    options.add_experimental_option('detach', True)
    
    service = Service(ChromeDriverManager().install())
    
    driver = webdriver.Chrome(service=service, options=options)
    
    
     # 인스타그램 아이디를 쿼리 문자열로 추가
    url = f'https://www.instagram.com/{instagram_id}/'
    
    
    driver.get(url)
    
    # 웹 요소 찾을때까지 
    driver.implicitly_wait(10) 

    # 크롤링 로직을 여기에 추가
    

    follow = driver.find_element(By.CLASS_NAME,'_ac2a')
    
    
    
    # driver.implicitly_wait(3)
    # id = driver.find_element(By.NAME, 'username')
    # id.send_keys('daegu_on')
    # time.sleep(2)
    
    # password = driver.find_element(By.NAME, 'password')
    # password.send_keys('tlstjd0045')
    # time.sleep(2)
    
    # login_button = driver.find_element(By.CSS_SELECTOR, 'button[type=submit]').click()
    # time.sleep(5)
    
    # # 로그인 정보 저장 여부 ("나중에 하기 버튼 클릭")
    # btn_later1 = driver.find_element(By.CLASS_NAME,'_ac8f')
    # btn_later1.click()
    # time.sleep(5)
    
    # # 알림 설정 ("나중에 하기 버튼 클릭")
    # btn_later2 = driver.find_element(By.CLASS_NAME,'_a9-z')
    # btn_later2.click()
    # time.sleep(5)
    
    title = 'ss'
    
    return title
