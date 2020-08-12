<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class AddDefaultLangToShop extends AbstractMigration
{
    /**
     * Change Method.
     *
     * Write your reversible migrations using this method.
     *
     * More information on writing migrations is available here:
     * https://book.cakephp.org/phinx/0/en/migrations.html#the-change-method
     *
     * Remember to call "create()" or "update()" and NOT "save()" when working
     * with the Table class.
     */
    public function change(): void
    {
      $AddDefaultLangToShop = $this->table('shops');
        $AddDefaultLangToShop->addColumn('default_lang_id', 'biginteger', ['after' => 'owner_id','signed' => false,'default' =>1])
        ->addForeignKey('default_lang_id', 'languages', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])->update();

    }
}
