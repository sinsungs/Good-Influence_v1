from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
# import time

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
    
    driver.get('https://naver.com')
    
    title = "API TEST"
    
    # 크롤링 로직을 여기에 추가
    
    # 예를 들어, 타이틀 가져오기
    # title = driver.find_element(By.TAG_NAME, 'title').text
    
    # driver.quit()
    
    return title
