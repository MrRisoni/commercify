<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableDisableCodZipCodes extends AbstractMigration
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
        $disableCODContinents = $this->table('shop_disable_cod_zipcode', ['signed' => false]);
        $disableCODContinents->addColumn('shop_id', 'biginteger', ['signed' => false])
            ->addColumn('country_code', 'string', ['limit' => 2])
            ->addColumn('zip_code', 'string', ['limit' => 20])
            ->addForeignKey('shop_id', 'shops', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->create();
    }
}
